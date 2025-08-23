
package Enums;

public enum ContaTipoEnum {
    
    ENTRADA(0, "Entrada"), 
    SAIDA(1, "Saída");
    
    private final int code;
    private final String titulo;
    
    private ContaTipoEnum(int code, String titulo){
        this.code = code;
        this.titulo = titulo;
    }
    
    public static ContaTipoEnum fromCode(int code){
        for(ContaTipoEnum tipo : values()){
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
