package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Produto;
import model.Semideus;

public class TransacaoDAO {

    private final Connection connection;

    public TransacaoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para registrar uma transação (compra)
    public void registrarTransacao(Semideus semideus, Produto produto, int quantidade) {
        String query = "INSERT INTO transacao (id_semideus, id_produto, quantidade_comprada, valor_total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, semideus.getId());
            stmt.setInt(2, produto.getId());
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, produto.getPreco() * quantidade);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
