package facade;

import dao.ProdutoDAO;
import dao.SemideusDAO;
import model.Produto;
import model.Semideus;

import java.sql.Connection;
import java.util.List;

public class LojaFacade {

    private final SemideusDAO semideusDAO;
    private final ProdutoDAO produtoDAO;

    public LojaFacade(Connection connection) {
        this.semideusDAO = new SemideusDAO(connection);
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void realizarCompra(int idSemideus, int idProduto, int quantidade) {
        Semideus semideus = semideusDAO.buscarSemideusPorId(idSemideus);
        Produto produto = produtoDAO.buscarProdutosDisponiveis()
                .stream()
                .filter(p -> p.getId() == idProduto)
                .findFirst()
                .orElse(null);

        if (semideus != null && produto != null && produto.getQuantidadeEstoque() >= quantidade) {
            double valorTotal = produto.getPreco() * quantidade;
            if (semideus.getSaldoDracma() >= valorTotal) {
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
                semideus.setSaldoDracma(semideus.getSaldoDracma() - valorTotal);

                semideusDAO.atualizarSaldo(semideus);
                produtoDAO.atualizarEstoque(produto);
            } else {
                throw new RuntimeException("Saldo insuficiente.");
            }
        } else {
            throw new RuntimeException("Produto não encontrado ou estoque insuficiente.");
        }
    }

    public List<Produto> listarProdutosDisponiveis() {
        return produtoDAO.buscarProdutosDisponiveis();
    }
}
