import java.util.List;

public class ExprCallFunction extends Expression{
    final Expression callee;
    // final Token paren;
    final List<Expression> arguments;

    ExprCallFunction(Expression callee, /*Token paren,*/ List<Expression> arguments) {
        this.callee = callee;
        // this.paren = paren;
        this.arguments = arguments;
    }
    public String toString() {
        return "ExprCallFunction['" + callee + "arguments=" + arguments + ']';
    }
    public Object solve(TablaSimbolos t){
        List<Object> literales = new ArrayList<>();


        String nombre = ((ExprVariable)callee).name.lexema;

        Object funcion = t.obtener(nombre);
        if(!(funcion instanceof StmtFunction)){
            // Error
        }
        else{
            for(int i=0; i<arguments.size(); i++){
                literales.add(arguments.get(i).solve(t));
            }
            Collections.reverse(literales);

            TablaSimbolos nuevas = ((StmtFunction) funcion).getTablaHash();

            if(nuevas.tamano() == literales.size()){
                nuevas.recorrerAsigna(literales);
                nuevas.AsignaStatements(t);

                return ((StmtFunction) funcion).body.exect(nuevas);
            }
            else{
                throw new RuntimeException("No coindicen los parámetros");
            }

        }
        return null;


    }
}
