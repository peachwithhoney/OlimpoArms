package controller;

import facade.LojaFacade;
import model.Produto;
import exceptions.SaldoInsuficienteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.EstoqueInsuficienteException;
import exceptions.DadosInvalidosException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private Label lblStatus;
    
    private final LojaFacade lojaFacade;

    public LojaController() {
        Connection connection = database.DatabaseConnectionFactory.getConnection();
        this.lojaFacade = new LojaFacade(connection);
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

        if (produtos.isEmpty()) {
            lblStatus.setText("Nenhum produto disponível no momento.");
        } else {
            lblStatus.setText("Produtos carregados com sucesso.");
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
            } catch (SaldoInsuficienteException e) {
                lblStatus.setText("Erro: " + e.getMessage()); 
            } catch (ProdutoNaoEncontradoException e) {
                lblStatus.setText("Erro: " + e.getMessage());  
            } catch (EstoqueInsuficienteException e) {
                lblStatus.setText("Erro: " + e.getMessage()); 
            } catch (DadosInvalidosException e) {
                lblStatus.setText("Erro: " + e.getMessage()); 
            } catch (NumberFormatException e) {
                lblStatus.setText("Erro: Verifique os dados inseridos. Devem ser números."); 
            } catch (Exception e) {
                lblStatus.setText("Erro desconhecido: " + e.getMessage());
                e.printStackTrace(); 
            }
        } else {
            lblStatus.setText("Por favor, selecione um produto e preencha todos os campos."); 
        }
    }
}
