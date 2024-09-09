package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Transacao;

public class TransacaoDAO {
    private final Connection connection;

    public TransacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarTransacao(Transacao transacao) throws SQLException {
        String query = "INSERT INTO transacao (id_semideus, id_item, quantidade, valor_total, data_hora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transacao.getIdSemideus());
            stmt.setInt(2, transacao.getIdItem()); 
            stmt.setInt(3, transacao.getQuantidade());
            stmt.setDouble(4, transacao.getValorTotal());
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(transacao.getDataHora()));
            stmt.executeUpdate();
        }
    }
}
