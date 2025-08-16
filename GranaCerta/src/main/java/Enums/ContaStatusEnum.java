package Enums;

import static Enums.ContaTipoEnum.values;

public enum ContaStatusEnum {
    PENDENTE(0), 
    REALIZADO(1), 
    CANCELADO(2);
    
    private final int code;
    
    private ContaStatusEnum(int code){
        this.code = code;
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
}

