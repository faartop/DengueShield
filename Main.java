import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Usuario;
import DAO.UsuarioDAO;
import Global.Util;


public class Main {
    public static void main(String[] args) throws Exception{

        Usuario usuario = new Usuario("Teste", "000000000", "teste@teste.com", "senha", 2);
        UsuarioDAO.novoUsuario(usuario);

        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    usuario.setId(rs.getInt("id"));
                }
        } catch (SQLException e) {
            System.err.println("Erro ao retornar dados: " + e.getMessage());
        }

        UsuarioDAO.inativarUsuario(usuario);
    }
}