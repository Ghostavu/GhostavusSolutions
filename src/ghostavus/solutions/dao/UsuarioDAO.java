package ghostavus.solutions.dao;

import ghostavus.solutions.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario validarLogin(String login, String senha) {

        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";

        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                
                usuario.setNome(rs.getString("nome"));
                usuario.setPerfil(rs.getString("perfil"));
                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
