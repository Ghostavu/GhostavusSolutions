package ghostavus.solutions.dao;

import ghostavus.solutions.model.ItemPedido;
import java.sql.*;
import ghostavus.solutions.dao.ConexaoMySQL;

public class ItemPedidoDAO {

    public void inserir(ItemPedido item) {

        String sql = "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario, total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getIdPedido());
            stmt.setInt(2, item.getIdProduto());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPrecoUnitario());
            stmt.setDouble(5, item.getTotal());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}