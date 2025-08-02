
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bancodados.BancoDadosUtil;
import br.com.proway.granacerta.bean.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;


public class ContaRepository implements ContaRepositoryInterface{

    @Override
    public void adicionar(Conta conta) throws SQLException{
       String sql = "INSERT INTO contas(nome, tipo, saldo, descricao) VALUES(?,?,?,?)";
       try(Connection conexao = BancoDadosUtil.getConnection()){
           PreparedStatement preparadorDeSQL = conexao.prepareStatement(sql);
           preparadorDeSQL.setString(1, conta.getNome());
           preparadorDeSQL.setInt(2, conta.getTipo());
           preparadorDeSQL.setDouble(3, conta.getSaldo());
           preparadorDeSQL.setString(4, conta.getDescricao());
           preparadorDeSQL.execute();      
       }
    }

    @Override
    public List<Conta> obterTodos() throws SQLException{
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody 
    }

    @Override
    public void editar(Conta conta) throws SQLException{
        String sql = "UPDATE contas SET nome = ?, tipo = ?, saldo = ?, descricao = ? WHERE = ?";
        try (Connection conexao = BancoDadosUtil.getConnection()){
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, conta.getNome());
            preparadorSQL.setInt(2, conta.getTipo());
            preparadorSQL.setDouble(3, conta.getSaldo());
            preparadorSQL.setString(4, conta.getDescricao());
            preparadorSQL.setInt(5, conta.getId());
            preparadorSQL.execute();
        }
    }

    @Override
    public void apagar(int id) throws SQLException{
        try(Connection conexao = BancoDadosUtil.getConnection()){
             String sql = "DELETE FROM contas WHERE ID = ?;";
             PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
             preparadorSQL.setInt(1, id);
             preparadorSQL.execute();
         }
    }  
}
