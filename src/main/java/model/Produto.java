package model;

public class Produto {
    private final int id;
    private final String nome;
    private final String tipo;
    private final double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, String tipo, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void atualizarEstoque(int quantidadeVendida) {
        if (quantidadeVendida > 0 && quantidadeVendida <= this.quantidadeEstoque) {
            this.quantidadeEstoque -= quantidadeVendida;  // Atualiza o estoque reduzindo pela quantidade vendida
        } else {
            throw new IllegalArgumentException("Quantidade inválida ou estoque insuficiente.");
        }
    }
}
