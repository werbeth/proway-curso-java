
package br.com.proway.granacerta.bean;

import Enums.ContaStatusEnum;
import Enums.ContaTipoEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaPagarReceber {
    private int id;
    private String nome;
    private LocalDate dataPrevista, dataRealizada;
    private double valor;
    private ContaStatusEnum status;
    private boolean registroAtivo;
    private LocalDateTime dataHoraCriacao;
    private ContaTipoEnum tipo;
    
    private Cliente cliente;
    private Conta conta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDate dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ContaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ContaStatusEnum status) {
        this.status = status;
    }

    public boolean isRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(boolean registroAtivo) {
        this.registroAtivo = registroAtivo;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public ContaTipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(ContaTipoEnum tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
}
