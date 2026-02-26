package ghostavus.solutions.dao;

import ghostavus.solutions.model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void cadastrar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco_custo, preco_venda, margem, quantidade_estoque, id_fornecedor, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPrecoCusto());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setDouble(4, produto.getMargem());
            stmt.setInt(5, produto.getQuantidadeEstoque());
            stmt.setInt(6, produto.getIdFornecedor());
            stmt.setInt(7, produto.getIdCategoria());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPrecoCusto(rs.getDouble("preco_custo"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setMargem(rs.getDouble("margem"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                produto.setIdFornecedor(rs.getInt("id_fornecedor"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                return produto;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void baixarEstoque(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id_produto = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPrecoCusto(rs.getDouble("preco_custo"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setMargem(rs.getDouble("margem"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                produto.setIdFornecedor(rs.getInt("id_fornecedor"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                lista.add(produto);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco_custo = ?, preco_venda = ?, margem = ?, quantidade_estoque = ?, id_fornecedor = ?, id_categoria = ? WHERE id_produto = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPrecoCusto());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setDouble(4, produto.getMargem());
            stmt.setInt(5, produto.getQuantidadeEstoque());
            stmt.setInt(6, produto.getIdFornecedor());
            stmt.setInt(7, produto.getIdCategoria());
            stmt.setInt(8, produto.getId_produto());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
