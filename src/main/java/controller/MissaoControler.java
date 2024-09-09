package controller;

import facade.MissaoFacade;
import model.Missao;

import java.util.List;

public class MissaoController {
    private MissaoFacade missaoFacade;

    public MissaoController(MissaoFacade missaoFacade) {
        this.missaoFacade = missaoFacade;
    }

    public List<Missao> listarMissoesDisponiveis() {
        return missaoFacade.listarMissoesDisponiveis();
    }

    public void selecionarMissao(int idSemideusLider, int idMissao, int idCompanheiro1, int idCompanheiro2) throws Exception {
        missaoFacade.selecionarMissao(idSemideusLider, idMissao, idCompanheiro1, idCompanheiro2);
    }
}
