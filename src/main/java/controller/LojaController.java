package controller;

import facade.LojaFacade;
import model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.util.List;

public class LojaController {

    @FXML
    private ComboBox<String> comboProdutos;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtIdSemideus;

    @FXML
    private TextArea areaInformacoes;

    @FXML
    private Button btnComprar;

    @FXML
    private Label lblStatus;

    private LojaFacade lojaFacade;

    public LojaController() {
        Connection connection = database.DatabaseConnectionFactory.getConnection();
        lojaFacade = new LojaFacade(connection);
    }

    @FXML
    public void initialize() {
        listarProdutos();
    }

    @FXML
    public void listarProdutos() {
        List<Produto> produtos = lojaFacade.listarProdutosDisponiveis();
        comboProdutos.getItems().clear();
        for (Produto produto : produtos) {
            comboProdutos.getItems().add(produto.getId() + " - " + produto.getNome());
        }
    }

    @FXML
    public void realizarCompra() {
        String produtoSelecionado = comboProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null && !txtQuantidade.getText().isEmpty() && !txtIdSemideus.getText().isEmpty()) {
            try {
                int idSemideus = Integer.parseInt(txtIdSemideus.getText());
                int idProduto = Integer.parseInt(produtoSelecionado.split(" - ")[0]);
                int quantidade = Integer.parseInt(txtQuantidade.getText());

                lojaFacade.realizarCompra(idSemideus, idProduto, quantidade);
                lblStatus.setText("Compra realizada com sucesso!");
                listarProdutos();
            } catch (Exception e) {
                lblStatus.setText("Erro ao realizar a compra: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            lblStatus.setText("Preencha todos os campos corretamente.");
        }
    }
}
