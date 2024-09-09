package model;

public class Bolsa {
    private int id;
    private Semideus semideus;
    private Item item;

    public Bolsa(int id, Semideus semideus, Item item) {
        this.id = id;
        this.semideus = semideus;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Semideus getSemideus() {
        return semideus;
    }

    public void setSemideus(Semideus semideus) {
        this.semideus = semideus;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
