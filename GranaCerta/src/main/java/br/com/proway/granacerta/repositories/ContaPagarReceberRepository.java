
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bancodados.BancoDadosUtil;
import br.com.proway.granacerta.bean.Conta;
import br.com.proway.granacerta.bean.ContaPagarReceber;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ContaPagarReceberRepository implements ContaPagarReceberRepositoryInterface{

    @Override
    public void adicionar(ContaPagarReceber conta) throws SQLException {
        try(Connection conexao = BancoDadosUtil.getConnection()){
            String sql = """
                         INSERT INTO contas_receber_pagar(
                            id_cliente,
                            id_contas,
                            nome,
                            tipo,
                            valor,
                            data_prevista,
                            data_realizada,
                            status                      
                         ) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                         """;
            
            var preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, conta.getCliente().getId());
            preparadorSQL.setNome(2, conta.getConta().getNome());
            preparadorSQL.setString(3, conta.getNome());
            preparadorSQL.setInt(4, conta.getTipo().getCode());
            preparadorSQL.setDouble(5, conta.getValor());
            preparadorSQL.setDate(6, Date.valueOf(conta.getDataPrevista));
            preparadorSQL.setInt(7, conta.getStatus().getCode());
            preparadorSQL.execute();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }

    @Override
    public List<Conta> obterTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Conta obterPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(ContaPagarReceberRepository conta) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void apagar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
