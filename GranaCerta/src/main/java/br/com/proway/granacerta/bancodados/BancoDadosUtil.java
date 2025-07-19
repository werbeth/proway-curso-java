
package br.com.proway.granacerta.bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class BancoDadosUtil {
    
    private static final String ARQUIVO_PROPRIEDADES = "/db.properties";
    
    public static Connection getConnection() throws SQLException{
        try(var in = BancoDadosUtil.class.getResourceAsStream(ARQUIVO_PROPRIEDADES)){
            Properties propriedades = new Properties();
            propriedades.load(in);
            
            String url = propriedades.getProperty("jdbc.url");
            String usuario = propriedades.getProperty("jdbc.user");
            String password = propriedades.getProperty("jdbc.password");
            return DriverManager.getConnection(url, usuario, password);
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException("Não foi possivel carregar as propriedades do Banco de Dados");
        }
    }
}
