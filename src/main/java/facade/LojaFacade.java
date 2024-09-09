package facade;

import dao.ProdutoDAO;
import dao.SemiDeusDAO;
import exceptions.DadosInvalidosException;
import exceptions.EstoqueInsuficienteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.SaldoInsuficienteException;
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
        if (quantidade <= 0) {
            throw new DadosInvalidosException("A quantidade deve ser maior que zero.");
        }

        Semideus semideus = semiDeusDAO.buscarSemideusPorId(idSemideus);
        if (semideus == null) {
            throw new DadosInvalidosException("Semideus não encontrado.");
        }

        Produto produto = produtoDAO.buscarProdutoPorId(idProduto);
        if (produto == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado.");
        }

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto solicitado.");
        }

        double valorTotal = produto.getPreco() * quantidade;
        if (semideus.getSaldoDracma() < valorTotal) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a compra.");
        }

        produto.atualizarEstoque(quantidade);
        semideus.setSaldoDracma(semideus.getSaldoDracma() - valorTotal);

        semiDeusDAO.atualizarSaldo(semideus);
        produtoDAO.atualizarEstoque(produto);
    }

    public List<Produto> listarProdutosDisponiveis() {
        return produtoDAO.buscarProdutosDisponiveis();
    }
}
