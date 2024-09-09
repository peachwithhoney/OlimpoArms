package controller;

import facade.LojaFacade;
import model.Produto;
import exceptions.SaldoInsuficienteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.EstoqueInsuficienteException;
import exceptions.DadosInvalidosException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    
    private LojaFacade lojaFacade;

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
        comboProdutos.getItems().clear();  // Limpa a ComboBox antes de preencher novamente

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
        String produtoSelecionado = comboProdutos.getSelectionModel().getSelectedItem();  // Produto selecionado na ComboBox

        if (produtoSelecionado != null && !txtQuantidade.getText().isEmpty() && !txtIdSemideus.getText().isEmpty()) {
            try {
                int idSemideus = Integer.parseInt(txtIdSemideus.getText());  // Converte o texto para ID do semideus
                int idProduto = Integer.parseInt(produtoSelecionado.split(" - ")[0]);  // Extrai o ID do produto da ComboBox
                int quantidade = Integer.parseInt(txtQuantidade.getText());  // Converte o texto para a quantidade de produtos

                lojaFacade.realizarCompra(idSemideus, idProduto, quantidade);  // Realiza a compra
                lblStatus.setText("Compra realizada com sucesso!");

                listarProdutos();  // Atualiza a lista de produtos após a compra
            } catch (SaldoInsuficienteException e) {
                lblStatus.setText("Erro: " + e.getMessage());  // Mostra erro de saldo insuficiente
            } catch (ProdutoNaoEncontradoException e) {
                lblStatus.setText("Erro: " + e.getMessage());  // Mostra erro de produto não encontrado
            } catch (EstoqueInsuficienteException e) {
                lblStatus.setText("Erro: " + e.getMessage());  // Mostra erro de estoque insuficiente
            } catch (DadosInvalidosException e) {
                lblStatus.setText("Erro: " + e.getMessage());  // Mostra erro de dados inválidos
            } catch (NumberFormatException e) {
                lblStatus.setText("Erro: Verifique os dados inseridos. Devem ser números.");  // Mostra erro de formato incorreto
            } catch (Exception e) {
                lblStatus.setText("Erro desconhecido: " + e.getMessage());  // Mostra erro desconhecido
                e.printStackTrace();  // Mostra o erro no console para depuração
            }
        } else {
            lblStatus.setText("Por favor, selecione um produto e preencha todos os campos.");  // Mostra mensagem de erro se os campos estiverem vazios
        }
    }
}
