package facade;

import dao.BolsaDAO;
import dao.ItemDAO;
import dao.SemideusDAO;
import exceptions.EstoqueInsuficienteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.SaldoInsuficienteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Item;
import model.Semideus;

public class LojaFacade {
    private final SemideusDAO semideusDAO;
    private final ItemDAO itemDAO;
    private final BolsaDAO bolsaDAO;

    public LojaFacade(Connection connection) {
        this.semideusDAO = new SemideusDAO(connection);
        this.itemDAO = new ItemDAO(connection);
        this.bolsaDAO = new BolsaDAO(connection);
    }

    public Semideus login(String nome, String senha) throws SQLException {
        return semideusDAO.buscarPorNomeESenha(nome, senha);
    }

    public List<Item> listarItensDisponiveis() throws SQLException {
        return itemDAO.listarItens();
    }

    public void comprarItem(int idSemideus, int idItem, int quantidade) throws SQLException, SaldoInsuficienteException, ProdutoNaoEncontradoException, EstoqueInsuficienteException {
        Semideus semideus = semideusDAO.buscarSemideusPorId(idSemideus);
        Item item = itemDAO.buscarItemPorId(idItem);
        
        if (item == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado.");
        }

        if (item.getQuantidadeEstoque() < quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente.");
        }

        double valorTotal = item.getPrecoDracma() * quantidade;
        if (semideus.getSaldoDracma() < valorTotal) {
            throw new SaldoInsuficienteException("Saldo insuficiente para comprar o item.");
        }

        semideus.setSaldoDracma(semideus.getSaldoDracma() - valorTotal);
        item.setQuantidadeEstoque(item.getQuantidadeEstoque() - quantidade);

        semideusDAO.atualizarSaldo(semideus);
        itemDAO.atualizarEstoque(item);

        bolsaDAO.adicionarItemNaBolsa(idSemideus, idItem, quantidade);
    }
}
