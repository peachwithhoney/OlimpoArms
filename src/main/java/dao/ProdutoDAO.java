package dao;

import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                produtos.add(new Produto(rs.getInt("id_produto"), rs.getString("nome_produto"),
                                         rs.getString("tipo_produto"), rs.getDouble("preco_dracma"), rs.getInt("quantidade_estoque")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
