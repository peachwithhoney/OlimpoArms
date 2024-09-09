package controller;

import facade.LojaFacade;
import model.Semideus;
import exceptions.UsuarioNaoEncontradoException;

public class LoginController {
    private LojaFacade lojaFacade;

    public LoginController(LojaFacade lojaFacade) {
        this.lojaFacade = lojaFacade;
    }

    public Semideus login(String nome, String senha) throws UsuarioNaoEncontradoException {
        Semideus semideus = lojaFacade.login(nome, senha);
        if (semideus == null) {
            throw new UsuarioNaoEncontradoException("Usuário ou senha incorretos");
        }
        return semideus;
    }
}
