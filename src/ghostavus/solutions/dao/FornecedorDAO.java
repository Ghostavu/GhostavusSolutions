package ghostavus.solutions.dao;

import ghostavus.solutions.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    // =========================
    // INSERT
    // =========================
    public void inserir(Fornecedor fornecedor) {

        String sql = "INSERT INTO fornecedores (razao_social, cnpj, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getRazaoSocial());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
        }
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Fornecedor buscarPorId(int id) {

    String sql = "SELECT * FROM fornecedores WHERE id_fornecedor = ?";

    try (Connection conn = ConexaoMySQL.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Fornecedor f = new Fornecedor();
            f.setId(rs.getInt("id_fornecedor"));
            f.setRazaoSocial(rs.getString("razao_social")); // CONFERE O NOME DA COLUNA
            return f;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Fornecedor> listar() {

        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedores";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));

                lista.add(fornecedor);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }

        return lista;
    }

    // =========================
    // ATUALIZAR
    // =========================
    public void atualizar(Fornecedor fornecedor) {

        String sql = "UPDATE fornecedores SET razao_social = ?, cnpj = ?, telefone = ?, email = ? WHERE id_fornecedor = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getRazaoSocial());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setInt(5, fornecedor.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    // =========================
    // EXCLUIR
    // =========================
    public void excluir(int id) {

        String sql = "DELETE FROM fornecedores WHERE id_fornecedor = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao excluir fornecedor: " + e.getMessage());
        }
    }
}