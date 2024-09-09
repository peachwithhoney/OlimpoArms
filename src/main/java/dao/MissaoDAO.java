package dao;

import model.Missao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissaoDAO {
    private Connection connection;

    public MissaoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Missao> buscarMissoesDisponiveis() throws SQLException {
        List<Missao> missoes = new ArrayList<>();
        String query = "SELECT * FROM missao WHERE status = 'disponivel'";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                missoes.add(new Missao(
                    rs.getInt("id_missao"),
                    rs.getString("descricao_profecia"),
                    rs.getString("local"),
                    rs.getString("status")
                ));
            }
        }
        return missoes;
    }

    public void associarSemideusAMissao(int idMissao, int idSemideus, String papel) throws SQLException {
        String query = "INSERT INTO missao_semideus (id_missao, id_semideus, papel) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idMissao);
            stmt.setInt(2, idSemideus);
            stmt.setString(3, papel);
            stmt.executeUpdate();
        }
    }

    public void atualizarStatusMissao(int idMissao, String status) throws SQLException {
        String query = "UPDATE missao SET status = ? WHERE id_missao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, idMissao);
            stmt.executeUpdate();
        }
    }
}
