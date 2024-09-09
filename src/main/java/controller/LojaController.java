package controller;

import facade.LojaFacade;
import model.Item;
import model.Semideus;
import exceptions.SaldoInsuficienteException;

import java.util.List;

public class LojaController {
    private LojaFacade lojaFacade;

    public LojaController(LojaFacade lojaFacade) {
        this.lojaFacade = lojaFacade;
    }

    public List<Item> listarItensDisponiveis() {
        return lojaFacade.listarItensDisponiveis();
    }

    public void comprarItem(int idSemideus, int idItem) throws SaldoInsuficienteException {
        lojaFacade.comprarItem(idSemideus, idItem);
    }
}
