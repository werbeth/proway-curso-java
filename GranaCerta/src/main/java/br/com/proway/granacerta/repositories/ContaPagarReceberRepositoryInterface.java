
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bean.Conta;
import br.com.proway.granacerta.bean.ContaPagarReceber;
import java.sql.SQLException;
import java.util.List;

public interface ContaPagarReceberRepositoryInterface {
    
    void adicionar(ContaPagarReceber conta) throws SQLException;
    List<ContaPagarReceber> obterTodos() throws SQLException;
    Conta obterPorId(int id) throws SQLException;
    void editar(ContaPagarReceber conta) throws SQLException;
    void apagar(int id) throws SQLException;
    
}
