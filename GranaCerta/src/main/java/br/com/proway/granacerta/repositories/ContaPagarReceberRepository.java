
package br.com.proway.granacerta.repositories;

import Enums.ContaStatusEnum;
import Enums.ContaTipoEnum;
import br.com.proway.granacerta.bancodados.BancoDadosUtil;
import br.com.proway.granacerta.bean.Cliente;
import br.com.proway.granacerta.bean.Conta;
import br.com.proway.granacerta.bean.ContaPagarReceber;
import br.com.proway.granacerta.modelos.ContaPagarReceberFiltro;
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
    public List<ContaPagarReceber> obterTodos(ContaPagarReceberFiltro filtros) throws SQLException {
        var contasPagarReceber = new ArrayList<ContaPagarReceber>();
        try(var conexao = BancoDadosUtil.getConnection()){
            var sql = new StringBuilder("""
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
                                contas ct ON crp.id_contas = ct.id
                            WHERE 1=1""");
             
            if(filtros.getTipo() != null){
                sql.append(" AND crp.tipo = ?");
            }
            
            if(filtros.getCliente() != null){
                sql.append(" AND crp.id_cliente = ?");
            }
            if(filtros.getConta() != null){            
                sql.append(" AND crp.id_conta = ?");
            }
            
            if(filtros.getStatus() != null){
                sql.append(" AND crp.status = ?");
            }
            
            if(filtros.getPesquisaNome() != null){
                sql.append(" AND crp.nome like ?");
            }
            
            if(filtros.getQuantidadeRegistros() > 0){
                sql.append(" LIMIT ?");
            }
            
            var preparadorSQL = conexao.prepareStatement(sql.toString());
            var index = 1;
            
            if(filtros.getTipo() != null){
                preparadorSQL.setInt(index++, filtros.getTipo().getCode());
            }
            
            if(filtros.getCliente() != null){
                preparadorSQL.setInt(index++, filtros.getCliente().getId());
            }
            
            if(filtros.getConta() != null){
                preparadorSQL.setInt(index++, filtros.getConta().getId());
            }
            
            if(filtros.getStatus() != null) {
                preparadorSQL.setInt(index++, filtros.getStatus().getCode());
            }
            
            if(filtros.getPesquisaNome() != null){
                preparadorSQL.setString(index++, "%" + filtros.getPesquisaNome() + "%");
            }
            
            if(filtros.getOrdenacaoColuna() != null){
                String colunaOrdenacao = obterColunaOrdenacao(filtros.getOrdenacaoColuna());
                String ordem = filtros.getOrdenacaoColuna().equals("Crescente") ? "ASC" : "DESC";
                sql.append(" ORDER BY ").append(colunaOrdenacao).append(" ").append(ordem);
            }
            
            if(filtros.getQuantidadeRegistros() > 0){
                preparadorSQL.setInt(index++, filtros.getQuantidadeRegistros());
            }
            
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
    
    
    private String obterColunaOrdenacao(String coluna){
        switch(coluna){
            case "Cliente": return "c.nome";
            case "Conta": return "ct.nome";
            case "Nome": return "crp.nome";
            case "Valor": return "crp.valor";
            case "Tipo": return "crp.tipo";
            case "Status": return "crp.status";
            default: return "cpr.id";
        }
    }
}
