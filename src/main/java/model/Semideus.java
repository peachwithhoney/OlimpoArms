package model;

public class Semideus {
    private final int id;
    private final String nome;
    private final String deusAfiliado;
    private double saldoDracma;

    public Semideus(int id, String nome, String deusAfiliado, double saldoDracma) {
        this.id = id;
        this.nome = nome;
        this.deusAfiliado = deusAfiliado;
        this.saldoDracma = saldoDracma;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDeusAfiliado() { return deusAfiliado; }
    public double getSaldoDracma() { return saldoDracma; }

    public void setSaldoDracma(double saldoDracma) {
        this.saldoDracma = saldoDracma;
    }
}
