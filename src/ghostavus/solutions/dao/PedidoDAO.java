package ghostavus.solutions.dao;

import ghostavus.solutions.model.Pedido;
import ghostavus.solutions.dao.ConexaoMySQL;
import java.sql.*;

public class PedidoDAO {

    public int inserir(Pedido pedido) {
       
        String sql = "INSERT INTO pedidos (id_cliente, valor_total, id_usuario_venda) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setDouble(2, pedido.getValorTotal());
            stmt.setInt(3, pedido.getIdUsuarioVenda());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
