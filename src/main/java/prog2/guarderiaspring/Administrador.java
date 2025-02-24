package prog2.guarderiaspring;

public class Administrador extends Usuario {
    
    private final String nivelAcceso = "administrador";
    
    public Administrador(String u, String p){
        setUsuario(u);
        setPassword(p);     
    }
   
    @Override
    public String getNivelAcceso(){
        return nivelAcceso;
    }
    

}
    