package ghostavus.solutions.main;

import java.sql.Connection;
import java.sql.SQLException;
import ghostavus.solutions.dao.ConexaoMySQL;

public class Main {
    public static void main(String[] args) {
        
        Connection conn = ConexaoMySQL.getConnection();
        
        if (conn != null) {
            try {
                System.out.println("Conexão obtida com sucesso!");
                
             
                conn.close();
                System.out.println("Conexão de teste fechada com sucesso.");
                
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão de teste.");
                e.printStackTrace();
            }
        } else {
            System.err.println("Falha ao obter conexão. Verifique se o MySQL está rodando e se as credenciais no ConexaoMySQL estão corretas.");
        }
    }
}
