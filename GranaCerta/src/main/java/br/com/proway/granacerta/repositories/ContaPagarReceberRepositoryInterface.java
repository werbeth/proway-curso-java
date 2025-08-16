
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bean.Conta;
import java.sql.SQLException;
import java.util.List;

public interface ContaPagarReceberRepositoryInterface {
    
    void adicionar(ContaPagarReceberRepository conta) throws SQLException;
    List<Conta> obterTodos() throws SQLException;
    Conta obterPorId(int id) throws SQLException;
    void editar(ContaPagarReceberRepository conta) throws SQLException;
    void apagar(int id) throws SQLException;
    
}
