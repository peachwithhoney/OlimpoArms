package dao;

import model.MissaoSemideus;
import model.Missao;
import model.Semideus;

import java.sql.*;

public class MissaoSemideusDAO {
    private Connection connection;

    public MissaoSemideusDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean verificarDisponibilidadeSemideus(int idSemideus) throws SQLException {
        String query = "SELECT * FROM missao_semideus WHERE id_semideus = ? AND papel = 'companheiro' OR papel = 'líder'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSemideus);
            ResultSet rs = stmt.executeQuery();
            return !rs.next(); // true se não estiver em outra missão
        }
    }
}
