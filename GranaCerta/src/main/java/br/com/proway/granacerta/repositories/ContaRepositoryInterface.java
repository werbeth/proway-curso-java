
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bean.Conta;
import java.sql.SQLException;
import java.util.List;

public interface ContaRepositoryInterface {
    
    void adicionar(Conta conta) throws SQLException;
    List<Conta> obterTodos() throws SQLException;
    void editar(Conta conta) throws SQLException;
    void apagar(int id) throws SQLException;
    
}
