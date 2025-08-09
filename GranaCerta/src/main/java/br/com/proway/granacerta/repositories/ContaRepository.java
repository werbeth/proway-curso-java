
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bancodados.BancoDadosUtil;
import br.com.proway.granacerta.bean.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        List<Conta> contas = new ArrayList<>();
        try (Connection conexao = BancoDadosUtil.getConnection()) {
            String sql = "SELECT id, nome,tipo, saldo, descricao FROM contas;";
            Statement executorSQL = conexao.createStatement();
            executorSQL.execute(sql);
            ResultSet registros = executorSQL.getResultSet();

            while (registros.next()) {
                int id = registros.getInt("id");
                String nome = registros.getString("nome");
                double saldo = registros.getDouble("saldo");
                int tipo = registros.getInt("tipo");
                
                Conta conta = new Conta();
                conta.setId(id);
                conta.setNome(nome);
                conta.setTipo(tipo);
                conta.setSaldo(saldo);
                
                contas.add(conta);
            }
        }
        
        return contas;
    }
    
    @Override
    public Conta obterPorId(int id) throws SQLException{
         String sql = "SELECT nome, saldo, tipo, descricao FROM contas WHERE ID = ?;";
         try(Connection conexao = BancoDadosUtil.getConnection()){
             PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
             preparadorSQL.setInt(1, id);
             preparadorSQL.execute();
             ResultSet registros = preparadorSQL.getResultSet();
             if(registros.next()){
                String nome = registros.getString("nome");
                double saldo = registros.getDouble("saldo");
                int tipo = registros.getInt("tipo");
                String descricao = registros.getString("descricao");
                
                Conta conta = new Conta();
                conta.setId(id);
                conta.setNome(nome);
                conta.setTipo(tipo);
                conta.setSaldo(saldo);
                conta.setDescricao(descricao);
                
                return conta;
             }
         }
         
         return null;
    }

    @Override
    public void editar(Conta conta) throws SQLException{
        String sql = "UPDATE contas SET nome = ?, tipo = ?, saldo = ?, descricao = ? WHERE id = ?;";
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
