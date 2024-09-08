package model;

public class Semideus {
    private final int id;
    private final String nome;
    private final String deusAfiliado;
    private final int nivelPoder;
    private double saldoDracma;

    public Semideus(int id, String nome, String deusAfiliado, int nivelPoder, double saldoDracma) {
        this.id = id;
        this.nome = nome;
        this.deusAfiliado = deusAfiliado;
        this.nivelPoder = nivelPoder;
        this.saldoDracma = saldoDracma;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDeusAfiliado() {
        return deusAfiliado;
    }

    public int getNivelPoder() {
        return nivelPoder;
    }

    public double getSaldoDracma() {
        return saldoDracma;
    }

    public void setSaldoDracma(double saldoDracma) {
        this.saldoDracma = saldoDracma;
    }

    public void comprarProduto(Produto produto, int quantidade) {
        double valorTotal = produto.getPreco() * quantidade;
        if (saldoDracma >= valorTotal && produto.getQuantidadeEstoque() >= quantidade) {
            saldoDracma -= valorTotal;
            produto.atualizarEstoque(quantidade);
        } else {
            throw new RuntimeException("Saldo insuficiente ou estoque insuficiente");
        }
    }
}
