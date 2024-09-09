package model;

public class Semideus {
    private int id;
    private String nome;
    private String chale;
    private double saldoDracma;
    private String senha;
    private boolean isAdministrador;

    public Semideus(int id, String nome, String chale, double saldoDracma, String senha, boolean isAdministrador) {
        this.id = id;
        this.nome = nome;
        this.chale = chale;
        this.saldoDracma = saldoDracma;
        this.senha = senha;
        this.isAdministrador = isAdministrador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChale() {
        return chale;
    }

    public void setChale(String chale) {
        this.chale = chale;
    }

    public double getSaldoDracma() {
        return saldoDracma;
    }

    public void setSaldoDracma(double saldoDracma) {
        this.saldoDracma = saldoDracma;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return isAdministrador;
    }

    public void setAdministrador(boolean administrador) {
        isAdministrador = administrador;
    }
}
