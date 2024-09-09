package model;

public class Missao {
    private int id;
    private String descricaoProfecia;
    private String local;
    private String status;

    public Missao(int id, String descricaoProfecia, String local, String status) {
        this.id = id;
        this.descricaoProfecia = descricaoProfecia;
        this.local = local;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoProfecia() {
        return descricaoProfecia;
    }

    public void setDescricaoProfecia(String descricaoProfecia) {
        this.descricaoProfecia = descricaoProfecia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
