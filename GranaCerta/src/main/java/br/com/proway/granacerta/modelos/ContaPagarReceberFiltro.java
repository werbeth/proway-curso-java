
package br.com.proway.granacerta.modelos;

import Enums.ContaStatusEnum;
import Enums.ContaTipoEnum;
import br.com.proway.granacerta.bean.Cliente;
import br.com.proway.granacerta.bean.Conta;

public class ContaPagarReceberFiltro {
    private Conta conta;
    private Cliente cliente;
    private ContaTipoEnum tipo;
    private ContaStatusEnum status;
    private String pesquisaNome;
    private String ordenacaoColuna;
    private String ordenacaoOrdem;
    private int quantidadeRegistros;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ContaTipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(ContaTipoEnum tipo) {
        this.tipo = tipo;
    }

    public ContaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ContaStatusEnum status) {
        this.status = status;
    }

    public String getPesquisaNome() {
        return pesquisaNome;
    }

    public void setPesquisaNome(String pesquisaNome) {
        this.pesquisaNome = pesquisaNome;
    }

    public String getOrdenacaoColuna() {
        return ordenacaoColuna;
    }

    public void setOrdenacaoColuna(String ordenacaoColuna) {
        this.ordenacaoColuna = ordenacaoColuna;
    }

    public String getOrdenacaoOrdem() {
        return ordenacaoOrdem;
    }

    public void setOrdenacaoOrdem(String ordenacaoOrdem) {
        this.ordenacaoOrdem = ordenacaoOrdem;
    }

    public int getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(int quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }
    
    
}
