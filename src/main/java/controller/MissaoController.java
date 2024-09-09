package controller;

import dao.MissaoDAO;
import model.Missao;

import java.util.List;

public class MissaoController {
    private MissaoDAO missaoDAO;

    public MissaoController(MissaoDAO missaoDAO) {
        this.missaoDAO = missaoDAO;
    }

    public List<Missao> listarMissoesDisponiveis() throws Exception {
        return missaoDAO.buscarMissoesDisponiveis();
    }

    public void selecionarMissao(int idSemideusLider, int idMissao, int idCompanheiro1, int idCompanheiro2) throws Exception {
        missaoDAO.associarSemideusAMissao(idMissao, idSemideusLider, "líder");
        missaoDAO.associarSemideusAMissao(idMissao, idCompanheiro1, "companheiro");
        missaoDAO.associarSemideusAMissao(idMissao, idCompanheiro2, "companheiro");
    }
}
