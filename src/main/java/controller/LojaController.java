package controller;

import exceptions.SaldoInsuficienteException;
import facade.LojaFacade;
import java.sql.SQLException;
import java.util.List;
import model.Item;

public class LojaController {
    private final LojaFacade lojaFacade;

    public LojaController(LojaFacade lojaFacade) {
        this.lojaFacade = lojaFacade;
    }

    public List<Item> listarItensDisponiveis() throws SQLException {
        return lojaFacade.listarItensDisponiveis();
    }

    public void comprarItem(int idSemideus, int idItem, int quantidade) throws SQLException, SaldoInsuficienteException {
        lojaFacade.comprarItem(idSemideus, idItem, quantidade); 
    }
}
