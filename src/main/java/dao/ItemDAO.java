package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemDAO {
    private final Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Item> listarItens() throws SQLException {
        List<Item> itens = new ArrayList<>();
        String query = "SELECT * FROM item";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                itens.add(new Item(
                    rs.getInt("id_item"),
                    rs.getString("nome_item"),
                    rs.getString("tipo_item"),
                    rs.getDouble("preco_dracma")
                ));
            }
        }
        return itens;
    }

    public Item buscarItemPorId(int idItem) throws SQLException {
        String query = "SELECT * FROM item WHERE id_item = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idItem);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Item(
                    rs.getInt("id_item"),
                    rs.getString("nome_item"),
                    rs.getString("tipo_item"),
                    rs.getDouble("preco_dracma")
                );
            }
        }
        return null;
    }
}
