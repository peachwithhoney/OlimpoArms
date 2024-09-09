package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> buscarProdutosDisponiveis() {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produto WHERE quantidade_estoque > 0";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nome_produto"),
                        rs.getString("tipo_produto"),
                        rs.getDouble("preco_dracma"),
                        rs.getInt("quantidade_estoque")
                ));
            }
        } catch (SQLException e) {
    }
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        String query = "SELECT * FROM produto WHERE id_produto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                            rs.getInt("id_produto"),
                            rs.getString("nome_produto"),
                            rs.getString("tipo_produto"),
                            rs.getDouble("preco_dracma"),
                            rs.getInt("quantidade_estoque")
                    );
                }
            }
        } catch (SQLException e) {
    }
        return null;
    }

    public void atualizarEstoque(Produto produto) {
        String query = "UPDATE produto SET quantidade_estoque = ? WHERE id_produto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, produto.getQuantidadeEstoque()); // Atualiza a quantidade de estoque
            stmt.setInt(2, produto.getId()); // Referencia o produto pelo ID
            stmt.executeUpdate();
        } catch (SQLException e) {
    }
    }

    public void adicionarProduto(Produto produto) {
        String query = "INSERT INTO produto (nome_produto, tipo_produto, preco_dracma, quantidade_estoque) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.executeUpdate();
        } catch (SQLException e) {
    }
    }

    public void removerProduto(int id) {
        String query = "DELETE FROM produto WHERE id_produto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
    }
    }
}
