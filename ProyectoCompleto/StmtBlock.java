package mx.ipn.escom.k.parser;

import javax.swing.plaf.nimbus.State;
import java.util.List;

public class StmtBlock extends Statement{
    final List<Statement> statements;

    StmtBlock(List<Statement> statements) {
        this.statements = statements;
    }
    public String toString() {
        return "StmtBlock['" + statements +  ']';
    }

    public Object exect(TablaSimbolos t){
        TablaSimbolos nueva = getTablaHash();
        t.CopiarA(nueva);
        if(statements.isEmpty()){
            throw new RuntimeException("No se colocó un cuerpo a la función");
        }
        else{

            for(int i = 0; i<statements.size(); i++){
                if(statements.get(i) instanceof StmtReturn){
                    return statements.get(i).exect(nueva);

                }
                else{
                    statements.get(i).exect(nueva);
                }

            }
            t.AsignaValoresDeOtraTabla(nueva);

                return null;
        }



    }
}
