package ghostavus.solutions.dao;

import ghostavus.solutions.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // =========================
    // INSERT
    // =========================
    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
        }
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                return categoria;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria por ID: " + e.getMessage());
        }
        return null;
    }

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY nome";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        return lista;
    }

    // =========================
    // ATUALIZAR
    // =========================
    public void atualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nome = ? WHERE id_categoria = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getIdCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    // =========================
    // EXCLUIR
    // =========================
    public void excluir(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir categoria: " + e.getMessage());
        }
    }
}
