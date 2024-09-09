package model;

import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private int idSemideus;
    private int idItem; 
    private int quantidade;
    private double valorTotal;
    private LocalDateTime dataHora;

    public Transacao(int id, int idSemideus, int idItem, int quantidade, double valorTotal, LocalDateTime dataHora) {
        this.id = id;
        this.idSemideus = idSemideus;
        this.idItem = idItem; 
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataHora = dataHora;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSemideus() {
        return idSemideus;
    }

    public void setIdSemideus(int idSemideus) {
        this.idSemideus = idSemideus;
    }

    public int getIdItem() { 
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
