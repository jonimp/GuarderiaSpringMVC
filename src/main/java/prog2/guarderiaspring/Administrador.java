package prog2.guarderiaspring;

public class Administrador extends Usuario {
    
    private String nivelAcceso;
    
    public Administrador(String u, String p, String nivelAcceso){
        setUsuario(u);
        setPassword(p);
        this.nivelAcceso = nivelAcceso;        
    }
   
    @Override
    public String getTipo(){
        return "administrador";
    }
    
    public String getNivelAcceso(){
        return nivelAcceso;
    }

}
    