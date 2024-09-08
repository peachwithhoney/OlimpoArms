package model;  


public class Transacao {
    private int id;
    private final Semideus semideus;
    private final Produto produto;  
    private final int quantidade;
    private final double valorTotal;

    public Transacao(int id, Semideus semideus, Produto produto, int quantidade, double valorTotal) {
        this.id = id;
        this.semideus = semideus;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public Semideus getSemideus() {
        return semideus;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
