
package br.com.proway.granacerta.repositories;

import br.com.proway.granacerta.bean.Cliente;
import br.com.proway.granacerta.bean.Proprietario;
import java.sql.SQLException;
import java.util.List;

public interface ClienteRepositoryInterface {
   
    void adicionar(Cliente cliente) throws SQLException;
    List<Cliente> obterTodos() throws SQLException;
    Cliente obterPorId(int id) throws SQLException;
    void editar(Cliente cliente) throws SQLException;
    void apagar(int id) throws SQLException;
    
}
