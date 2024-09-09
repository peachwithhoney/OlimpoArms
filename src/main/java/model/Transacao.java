package model;

import java.time.LocalDateTime;

public class Transacao {
    private final int id;
    private final int idSemideus;
    private final int idProduto;
    private final int quantidade;
    private final double valorTotal;
    private final LocalDateTime dataHora;

    public Transacao(int id, int idSemideus, int idProduto, int quantidade, double valorTotal, LocalDateTime dataHora) {
        this.id = id;
        this.idSemideus = idSemideus;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataHora = dataHora;
    }

    public int getId() { return id; }
    public int getIdSemideus() { return idSemideus; }
    public int getIdProduto() { return idProduto; }
    public int getQuantidade() { return quantidade; }
    public double getValorTotal() { return valorTotal; }
    public LocalDateTime getDataHora() { return dataHora; }
}
