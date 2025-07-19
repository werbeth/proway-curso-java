
package br.com.proway.granacerta.exemplos;
import br.com.proway.granacerta.bean.Proprietario;
import br.com.proway.granacerta.bean.Conta;
import javax.swing.JOptionPane;


public class InstanciarObjetos {
    public static void Main(String[] args){
        Proprietario joao = new Proprietario();    
    
        joao.setId(75);
        joao.setNome("Jão cardoso");
        joao.setEmail("joao@gmail.com");
        joao.setCpf("612382-758-89");
        joao.setSenha("123456");
    
        JOptionPane.showMessageDialog(null,
            "id: " + joao.getId() +
            "\nNome: " + joao.getNome() + 
            "\nemail: " + joao.getEmail() + 
            "\ncpf: " + joao.getCpf());
    
        Conta viacredi = new Conta();
        //Viacredi.getTipo("Poupança");
        //Viacredi.getTipo("Corrente");
        
        Conta Itau = new Conta();
        //Itau.tipo = "Salário";
        //Itau.tipo = "Poupança";
        //Conta Santander = new Conta();
        //Santander.tipo = "Salário";
        //Santander.tipo = "Poupança";
    }
}
