package facade;

import dao.ProdutoDAO;
import dao.SemiDeusDAO;
import dao.TransacaoDAO;
import exceptions.SaldoInsuficienteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.EstoqueInsuficienteException;
import exceptions.ErroAoRegistrarTransacaoException;
import model.Produto;
import model.Semideus;
import model.Transacao;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class LojaFacade {
    private SemiDeusDAO semideusDAO;
    private final ProdutoDAO produtoDAO;
    private final TransacaoDAO transacaoDAO;

    public LojaFacade(Connection connection) {
        this.SemiDeusDAO = new SemiDeusDAO(connection);
        this.produtoDAO = new ProdutoDAO(connection);
        this.transacaoDAO = new TransacaoDAO(connection);
    }

    public void realizarCompra(int idSemideus, int idProduto, int quantidade) {
        Semideus semideus = semideusDAO.buscarSemideusPorId(idSemideus);
        Produto produto = produtoDAO.buscarProdutosDisponiveis()
                .stream()
                .filter(p -> p.getId() == idProduto)
                .findFirst()
                .orElse(null);

        if (semideus == null) {
            throw new ProdutoNaoEncontradoException("Semideus não encontrado.");
        }

        if (produto == null || produto.getQuantidadeEstoque() < quantidade) {
            throw new EstoqueInsuficienteException("Produto não encontrado ou estoque insuficiente.");
        }

        double valorTotal = produto.getPreco() * quantidade;

        if (semideus.getSaldoDracma() < valorTotal) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        semideus.setSaldoDracma(semideus.getSaldoDracma() - valorTotal);

        semideusDAO.atualizarSaldo(semideus);
        produtoDAO.atualizarEstoque(produto);

        try {
            Transacao transacao = new Transacao(0, idSemideus, idProduto, quantidade, valorTotal, LocalDateTime.now());
            transacaoDAO.registrarTransacao(transacao);
        } catch (Exception e) {
            throw new ErroAoRegistrarTransacaoException("Erro ao registrar a transação: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutosDisponiveis() {
        return produtoDAO.buscarProdutosDisponiveis();
    }
}
