package facade;

import dao.ProdutoDAO;
import dao.SemiDeusDAO;
import java.sql.Connection;
import java.util.List;
import model.Produto;
import model.Semideus;

public class LojaFacade {

    private final SemiDeusDAO semiDeusDAO;
    private final ProdutoDAO produtoDAO;

    public LojaFacade(Connection connection) {
        this.semiDeusDAO = new SemiDeusDAO(connection);
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void realizarCompra(int idSemideus, int idProduto, int quantidade) {
        Semideus semideus = semiDeusDAO.buscarSemideusPorId(idSemideus);
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

                semiDeusDAO.atualizarSaldo(semideus);
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
