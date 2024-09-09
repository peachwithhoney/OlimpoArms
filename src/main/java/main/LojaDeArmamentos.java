package main;

import database.DatabaseConnectionFactory;
import exceptions.ErroAoRegistrarTransacaoException;
import exceptions.EstoqueInsuficienteException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.SaldoInsuficienteException;
import facade.LojaFacade;
import java.awt.*;
import java.sql.Connection;
import java.util.List;
import javax.swing.*;
import model.Item;

public class LojaDeArmamentos {
    private JFrame frame;
    private JComboBox<String> comboProdutos;
    private JTextField txtQuantidade;
    private JTextField txtIdSemideus;
    private JLabel lblStatus;
    private final LojaFacade lojaFacade;

    public LojaDeArmamentos() {
        Connection connection = DatabaseConnectionFactory.getConnection();
        lojaFacade = new LojaFacade(connection);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Loja de Armamentos para Semideuses");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblIdSemideus = new JLabel("ID do Semideus:");
        lblIdSemideus.setBounds(10, 10, 150, 25);
        frame.getContentPane().add(lblIdSemideus);

        txtIdSemideus = new JTextField();
        txtIdSemideus.setBounds(150, 10, 200, 25);
        frame.getContentPane().add(txtIdSemideus);

        JLabel lblProduto = new JLabel("Selecione o Item:");
        lblProduto.setBounds(10, 50, 150, 25);
        frame.getContentPane().add(lblProduto);

        comboProdutos = new JComboBox<>();
        comboProdutos.setBounds(150, 50, 200, 25);
        frame.getContentPane().add(comboProdutos);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(10, 90, 150, 25);
        frame.getContentPane().add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(150, 90, 200, 25);
        frame.getContentPane().add(txtQuantidade);

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setBounds(150, 130, 100, 30);
        btnComprar.addActionListener(e -> realizarCompra());
        frame.getContentPane().add(btnComprar);

        lblStatus = new JLabel("");
        lblStatus.setBounds(10, 170, 350, 25);
        frame.getContentPane().add(lblStatus);

        carregarItens();
        frame.setVisible(true);
    }

    private void carregarItens() {
        List<Item> itens = lojaFacade.listarItensDisponiveis();
        for (Item item : itens) {
            comboProdutos.addItem(item.getId() + " - " + item.getNomeItem());
        }
    }

    private void realizarCompra() {
        try {
            int idSemideus = Integer.parseInt(txtIdSemideus.getText());
            String itemSelecionado = (String) comboProdutos.getSelectedItem();
            int idItem = Integer.parseInt(itemSelecionado.split(" - ")[0]);
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            lojaFacade.realizarCompra(idSemideus, idItem, quantidade);
            lblStatus.setText("Compra realizada com sucesso!");
        } catch (NumberFormatException ex) {
            lblStatus.setText("Erro: Verifique os dados inseridos.");
        } catch (SaldoInsuficienteException ex) {
            lblStatus.setText("Erro: " + ex.getMessage());
        } catch (ProdutoNaoEncontradoException ex) {
            lblStatus.setText("Erro: " + ex.getMessage());
        } catch (EstoqueInsuficienteException ex) {
            lblStatus.setText("Erro: " + ex.getMessage());
        } catch (ErroAoRegistrarTransacaoException ex) {
            lblStatus.setText("Erro ao registrar a transação: " + ex.getMessage());
        } catch (Exception ex) {
            lblStatus.setText("Erro desconhecido: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LojaDeArmamentos());
    }
}
