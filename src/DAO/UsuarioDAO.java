package DAO;

import DTO.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();
    
    
    public void Cadastraruser(UsuarioDTO userdto){
            String sql = "insert into pj.usuarios(nome, sobrenome, email, endereco, telefone, nascimento, idesporte, pg) values (?,?,?,?,?,?,?, 'suspenso')";            
            conn = new ConexaoDAO().conectaDB();
             
            try {
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, userdto.getNome());
                    pstm.setString(2, userdto.getSobrenome());
                    pstm.setString(3, userdto.getEmail());
                    pstm.setString(4, userdto.getEndereco());
                    pstm.setString(5, userdto.getTelefone());
                    pstm.setString(6, userdto.getNascimento()); 
                    pstm.setString(7, userdto.getSport());
                       
                    pstm.execute();
                    pstm.close();
                    
                    JOptionPane.showMessageDialog(null, "Cadastro Confirmado com sucesso!");
                    
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "CadastroDAO Cadastro" + e);           
            }
        }
    
    public ResultSet autUser(UsuarioDTO userdto){
        conn = new ConexaoDAO().conectaDB();
        
        try {
            String sql = "select * from pj.usuarios where nome = ? and sobrenome = ? ";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userdto.getNome());
            pstm.setString(2, userdto.getSobrenome());
            
            rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO" + e);
            return null;
        }
        
       
    }
    
    
    public ArrayList<UsuarioDTO> PesquisarUsuario(){
        String sql = "select * from pj.usuarios";
        conn = new ConexaoDAO().conectaDB();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                UsuarioDTO userdto = new UsuarioDTO();
                userdto.setId(rs.getInt("id"));
                userdto.setNome(rs.getString("nome"));
                userdto.setSobrenome(rs.getString("sobrenome"));
                userdto.setPg(rs.getString("pg"));
                
                lista.add(userdto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Usuario pesquisar: "+ e);
        }
        return lista;
    }

    public void PgUpdate(UsuarioDTO userdto){
            String sql = "UPDATE pj.usuarios SET `pg` = 'apto' WHERE `usuarios`.`id` = 1";
            conn = new ConexaoDAO().conectaDB();
            try {
                pstm = conn.prepareStatement(sql);
                
                
                pstm.execute();
                pstm.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Update apto" + e); 
            }

        }    

}
