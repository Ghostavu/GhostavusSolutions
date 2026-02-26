/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghostavus.solutions.dao;

import ghostavus.solutions.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ClienteDAO {

        public boolean cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, telefone, email, endereco, sexo) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getSexo());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
            return false; 
        }
    }

        public int buscarIdPorCpf(String cpf) {
            
            String sql = "SELECT id_cliente FROM cliente WHERE cpf = ?";
            
            try (Connection conn = ConexaoMySQL.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    return rs.getInt("id_cliente");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    
    public boolean excluirPorCpf(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 

        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
            return false; 
        }
    }
    
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        
        try (Connection conn = ConexaoMySQL.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setSexo(rs.getString("sexo"));

            return cliente;
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
