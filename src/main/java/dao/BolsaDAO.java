package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Bolsa;
import model.Item;

public class BolsaDAO {
    private final Connection connection;

    public BolsaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarItemNaBolsa(int idSemideus, int idItem) throws SQLException {
        String query = "INSERT INTO bolsa (id_semideus, id_item) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSemideus);
            stmt.setInt(2, idItem);
            stmt.executeUpdate();
        }
    }

    public List<Bolsa> listarItensNaBolsa(int idSemideus) throws SQLException {
        List<Bolsa> itensBolsa = new ArrayList<>();
        String query = "SELECT b.id_bolsa, i.id_item, i.nome_item, i.tipo_item, i.preco_dracma " +
                "FROM bolsa b INNER JOIN item i ON b.id_item = i.id_item WHERE b.id_semideus = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSemideus);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = new Item(
                    rs.getInt("id_item"),
                    rs.getString("nome_item"),
                    rs.getString("tipo_item"),
                    rs.getDouble("preco_dracma")
                );
                itensBolsa.add(new Bolsa(rs.getInt("id_bolsa"), null, item));
            }
        }
        return itensBolsa;
    }
}
