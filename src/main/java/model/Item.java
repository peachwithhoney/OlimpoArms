package model;

public class Item {
    private int id;
    private String nomeItem;
    private String tipoItem;
    private double precoDracma;
    private int quantidadeEstoque;

    public Item(int id, String nomeItem, String tipoItem, double precoDracma) {
        this.id = id;
        this.nomeItem = nomeItem;
        this.tipoItem = tipoItem;
        this.precoDracma = precoDracma;
        this.quantidadeEstoque = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public double getPrecoDracma() {
        return precoDracma;
    }

    public void setPrecoDracma(double precoDracma) {
        this.precoDracma = precoDracma;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
