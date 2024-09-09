package facade;

import dao.ItemDAO;
import dao.SemideusDAO;
import dao.BolsaDAO;
import exceptions.SaldoInsuficienteException;
import model.Item;
import model.Semideus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public void comprarItem(int idSemideus, int idItem) throws SQLException, SaldoInsuficienteException {
        Semideus semideus = semideusDAO.buscarSemideusPorId(idSemideus);
        Item item = itemDAO.buscarItemPorId(idItem);
        if (semideus.getSaldoDracma() < item.getPrecoDracma()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para comprar o item.");
        }

        semideus.setSaldoDracma(semideus.getSaldoDracma() - item.getPrecoDracma());
        semideusDAO.atualizarSaldo(semideus);
        bolsaDAO.adicionarItemNaBolsa(idSemideus, idItem);
    }
}
