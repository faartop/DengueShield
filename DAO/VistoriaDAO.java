package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Vistoria;
import Global.Util;

public class VistoriaDAO {
    public VistoriaDAO() {}

    public static void novaVistoria(Vistoria vistoria) {
        String sql = "INSERT INTO Vistoria(ID, ID_denuncia, ID_vistoriador, endereco, descricao, dataVisita) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            Connection conn = Util.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vistoria.getID());
            pstmt.setInt(2, vistoria.getID_denuncia());
            pstmt.setInt(3, vistoria.getID_vistoriador());
            pstmt.setInt(4, vistoria.getEndereco());
            pstmt.setString(5, vistoria.getDescricao());
            pstmt.setString(6, vistoria.getdataVisita());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar nova vistoria: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void alterarVistoria(Vistoria vistoria) {
        String sql = "UPDATE Vistoria ID_denuncia = ?, ID_vistoriador = ?, endereco = ?, descricao = ?, dataVisita = ? WHERE ID = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = Util.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vistoria.getID_denuncia());
            pstmt.setInt(2, vistoria.getID_vistoriador());
            pstmt.setInt(3, vistoria.getEndereco());
            pstmt.setString(4, vistoria.getDescricao());
            pstmt.setString(5, vistoria.getdataVisita());
            pstmt.setInt(6, vistoria.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar denúncia: " + e.getMessage());
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void inativarVistoria(Vistoria vistoria) {
        Util.inativarCadastro(vistoria.getID(), "Vistoria");
    }

    public static void retornarVistoria() {
        Util.consultaTabela("Vistoria");
    }
}
