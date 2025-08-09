package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bancodados.BancoDadosUtil;
import br.com.proway.granacerta.bean.Cliente;
import br.com.proway.granacerta.bean.Proprietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteRepository implements ClienteRepositoryInterface {

    @Override
    public void adicionar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente(nome, cnpj) VALUES(?,?)";
        try (Connection conexao = BancoDadosUtil.getConnection()) {
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, cliente.getNome());
            preparadorSQL.setString(2, cliente.getCnpj());
            preparadorSQL.execute();
        }
    }
    
    @Override
    public List<Cliente> obterTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try(Connection conexao = BancoDadosUtil.getConnection()){
            String sql = "SELECT id, nome, cnpj FROM cliente;";
            Statement executorSQL = conexao.createStatement();
            executorSQL.execute(sql);
            ResultSet registros = executorSQL.getResultSet();
            
            while(registros.next()){
                int id = registros.getInt("id");
                String nome = registros.getString("nome");
                String cnpj = registros.getString("cnpj");
                
                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCnpj(cnpj);
                
                clientes.add(cliente);
            }
        }
        
        return clientes;
    }
    
    @Override
    public Cliente obterPorId(int id) throws SQLException{
        String sql = "SELECT nome, cnpj FROM cliente WHERE ID = ?;";
        try(Connection conexao = BancoDadosUtil.getConnection()){
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, id);
            preparadorSQL.execute();
            ResultSet registros = preparadorSQL.getResultSet();
            
            if(registros.next()){
                String nome = registros.getString("nome");
                String cnpj = registros.getString("cnpj");
                
                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCnpj(cnpj);
                
                return cliente;
            }
        } 
        return null;
    }
    
    @Override
    public void editar(Cliente cliente) throws SQLException{
        String sql = "UPDATE cliente SET nome = ?, cnpj = ? WHERE id = ?;";
        try(Connection conexao = BancoDadosUtil.getConnection()){
            PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setString(1, cliente.getNome());
            preparadorSQL.setString(2, cliente.getCnpj());
            preparadorSQL.setInt(3, cliente.getId());
            preparadorSQL.execute();
        }
    }
    
    @Override
    public void apagar(int id) throws SQLException{
        try(Connection conexao = BancoDadosUtil.getConnection()){
             String sql = "DELETE FROM cliente WHERE ID = ?;";
             PreparedStatement preparadorSQL = conexao.prepareStatement(sql);
             preparadorSQL.setInt(1, id);
             preparadorSQL.execute();
        }   
    }
}
