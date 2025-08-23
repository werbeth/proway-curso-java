package Enums;

import static Enums.ContaTipoEnum.values;

public enum ContaStatusEnum {
    PENDENTE(0, "Pendente"), 
    REALIZADO(1, "Realizado"), 
    CANCELADO(2, "Cancelado");
    
    private final int code;
    private final String titulo;
    
    private ContaStatusEnum(int code, String titulo){
        this.code = code;
        this.titulo = titulo;
    }
    
    public static ContaStatusEnum fromCode(int code){
        for(ContaStatusEnum tipo : values()){
            if(tipo.code == code){
                return tipo;
            }
        }
        
        throw new IllegalArgumentException("Código do tipo da Conta é inválido: " + code);
    }
        
    public int getCode(){
        return code;
    }
    
    public String getTitulo(){
        return titulo;
    }
}

