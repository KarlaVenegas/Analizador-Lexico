import java.util.ArrayList;
import java.util.List;


public class AnalizadorSemantico {
    private List<Statement> st;
    public  AnalizadorSemantico(List<Statement> st){
        this.st = st;
    }

    public void proceso(){

        List<Statement> Funciones = new ArrayList<>();
       // System.out.println(st.get(0).toString());
       // System.out.println(st.toString());

        TablaSimbolos general = new TablaSimbolos();

        for(Statement aux: st){
            aux.exect(general);
        }
    }
    
}
