package model;

public class MissaoSemideus {
    private int id;
    private Missao missao;
    private Semideus semideus;
    private String papel;

    public MissaoSemideus(int id, Missao missao, Semideus semideus, String papel) {
        this.id = id;
        this.missao = missao;
        this.semideus = semideus;
        this.papel = papel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
        this.missao = missao;
    }

    public Semideus getSemideus() {
        return semideus;
    }

    public void setSemideus(Semideus semideus) {
        this.semideus = semideus;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
