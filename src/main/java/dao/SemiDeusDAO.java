package dao;

import model.Semideus;
import java.sql.*;

public class SemideusDAO {
    private Connection connection;

    public SemideusDAO(Connection connection) {
        this.connection = connection;
    }

    public Semideus buscarPorNomeESenha(String nome, String senha) throws SQLException {
        String query = "SELECT * FROM semideus WHERE nome = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Semideus(
                    rs.getInt("id_semideus"),
                    rs.getString("nome"),
                    rs.getString("chale"),
                    rs.getDouble("saldo_dracma"),
                    rs.getString("senha"),
                    rs.getBoolean("is_administrador")
                );
            }
        }
        return null;
    }

    public void cadastrarNovoSemideus(Semideus semideus) throws SQLException {
        String query = "INSERT INTO semideus (nome, chale, saldo_dracma, senha, is_administrador) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, semideus.getNome());
            stmt.setString(2, semideus.getChale());
            stmt.setDouble(3, semideus.getSaldoDracma());
            stmt.setString(4, semideus.getSenha());
            stmt.setBoolean(5, semideus.isAdministrador());
            stmt.executeUpdate();
        }
    }

    public void atualizarSaldo(Semideus semideus) throws SQLException {
        String query = "UPDATE semideus SET saldo_dracma = ? WHERE id_semideus = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, semideus.getSaldoDracma());
            stmt.setInt(2, semideus.getId());
            stmt.executeUpdate();
        }
    }
}
