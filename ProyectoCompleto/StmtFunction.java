

import java.util.ArrayList;
import java.util.List;

public class StmtFunction extends Statement {
    final Token name;
    final List<Token> params;
    final StmtBlock body;

    StmtFunction(Token name, List<Token> params, StmtBlock body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }
        public String toString() {
        return "StmtFunction['" + name + "', params=" + params + ", body=" + body + ']';
    }
    public Object exect(TablaSimbolos t){
        if(t.existeIdentificador(name.lexema)){
            throw new RuntimeException("Se define a la misma función más de 1 vez.");
        }
        else{
            //recorro los parámetros para meterlos a la tabla de simbolos
            for(int i = 0; i< params.size(); i++){
                if(!this.getTablaHash().existeIdentificador(params.get(i).lexema)){
                    this.getTablaHash().asignar(params.get(i).lexema, null);
                }
                else{
                    throw new RuntimeException("Parámetros repetidos en la función " + name.lexema);
                }

            }
            t.asignar(name.lexema, this);
        }
        return null;

    }
}

