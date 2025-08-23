
package br.com.proway.granacerta.repositories;

import Enums.ContaStatusEnum;
import Enums.ContaTipoEnum;
import br.com.proway.granacerta.bancodados.BancoDadosUtil;
import br.com.proway.granacerta.bean.Cliente;
import br.com.proway.granacerta.bean.Conta;
import br.com.proway.granacerta.bean.ContaPagarReceber;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
                            status                      
                         ) VALUES (?, ?, ?, ?, ?, ?, ?);
                         """;
            
            var preparadorSQL = conexao.prepareStatement(sql);
            preparadorSQL.setInt(1, conta.getCliente().getId());
            preparadorSQL.setInt(2, conta.getConta().getId());
            preparadorSQL.setString(3, conta.getNome());
            preparadorSQL.setInt(4, conta.getTipo().getCode());
            preparadorSQL.setDouble(5, conta.getValor());
            preparadorSQL.setDate(6, Date.valueOf(conta.getDataPrevista()));
            preparadorSQL.setInt(7, conta.getStatus().getCode());
            preparadorSQL.execute();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }

    @Override
    public List<ContaPagarReceber> obterTodos() throws SQLException {
        var contasPagarReceber = new ArrayList<ContaPagarReceber>();
        try(var conexao = BancoDadosUtil.getConnection()){
            String sql = """
                            SELECT 
                                crp.id,
                                c.id AS cliente_id,
                                c.nome AS cliente_nome,
                                ct.id AS conta_id,
                                ct.nome AS conta_nome,
                                crp.nome AS transacao_nome,
                                crp.tipo,
                                crp.valor,
                                crp.data_prevista,
                                crp.data_realizada,
                                crp.status,
                                crp.registros_ativo,
                                crp.data_criacao
                            FROM 
                                contas_receber_pagar crp
                            JOIN 
                                cliente c ON crp.id_cliente = c.id
                            JOIN 
                                contas ct ON crp.id_contas = ct.id;
                         """;
            var preparadorSQL = conexao.prepareStatement(sql);

            var registros = preparadorSQL.executeQuery();
            while(registros.next()){
                ContaPagarReceber contaPagarReceber = new ContaPagarReceber();
                contaPagarReceber.setId(registros.getInt("id"));
                contaPagarReceber.setValor(registros.getDouble("valor"));
                contaPagarReceber.setNome(registros.getString("transacao_nome"));
                contaPagarReceber.setRegistroAtivo(registros.getBoolean("registros_ativo"));
                contaPagarReceber.setTipo(ContaTipoEnum.fromCode(registros.getInt("tipo")));
                contaPagarReceber.setStatus(ContaStatusEnum.fromCode(registros.getInt("status")));
                contaPagarReceber.setDataPrevista( registros.getDate("data_prevista").toLocalDate()); 
                contaPagarReceber.setDataHoraCriacao(registros.getTimestamp("data_criacao").toLocalDateTime());
                
                var dataRealizadaBanco = registros.getDate("data_realizada");
                if(dataRealizadaBanco != null){
                    contaPagarReceber.setDataRealizada(dataRealizadaBanco.toLocalDate());
                }
                var cliente = new Cliente();
                cliente.setId(registros.getInt("cliente_id"));
                cliente.setNome(registros.getString("cliente_nome"));
                contaPagarReceber.setCliente(cliente);
                
                var conta = new Conta();
                conta.setId(registros.getInt("conta_id"));
                conta.setNome(registros.getString("conta_nome"));
                contaPagarReceber.setConta(conta);
                
                contasPagarReceber.add(contaPagarReceber);
            }
            return contasPagarReceber;
        }
    }

    @Override
    public Conta obterPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(ContaPagarReceber conta) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void apagar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
