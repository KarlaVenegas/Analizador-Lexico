package mx.ipn.escom.k.parser;

import mx.ipn.escom.k.tools.Token;

public class StmtVar extends Statement {
    final Token name;
    final Expression initializer;

    StmtVar(Token name, Expression initializer) {
        this.name = name;
        this.initializer = initializer;
    }
    public String toString() {
        return "StmtVar['" + name + '=' + initializer + ']';
    }

    public Object exect(TablaSimbolos t){

        if(initializer == null){
            t.asignar(name.lexema, null);
        }
        else if(t.existeIdentificador(name.lexema)){
            throw new RuntimeException("Error al inicializar la variable " + name.lexema + " debido a que ya existe.");
        }
        else
            t.asignar(name.lexema, initializer.solve(t));


        return null;

    }
}
