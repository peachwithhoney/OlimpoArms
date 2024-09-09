package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Semideus;

public class SemiDeusDAO {
    private final Connection connection;

    public SemiDeusDAO(Connection connection) {
        this.connection = connection;
    }

    public Semideus buscarSemideusPorId(int id) {
        String query = "SELECT * FROM semideus WHERE id_semideus = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Semideus(
                    rs.getInt("id_semideus"),
                    rs.getString("nome"),
                    rs.getString("deus_afiliado"),
                    rs.getDouble("saldo_dracma")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void atualizarSaldo(Semideus semideus) {
        String query = "UPDATE semideus SET saldo_dracma = ? WHERE id_semideus = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, semideus.getSaldoDracma());
            stmt.setInt(2, semideus.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
