public class ExprAssign extends Expression{
    final Token name;
    final Expression value;

    ExprAssign(Token name, Expression value) {
        this.name = name;
        this.value = value;
    }
    public String toString() {
        return "ExprAssign['" + name + '=' + value + ']';
    }

    public Object solve(TablaSimbolos t){
        if(t.existeIdentificador(name.lexema)){
            t.asignar(name.lexema, value.solve(t));
        }
        else{
            throw new RuntimeException("Variable no definida '" + name.lexema + "'.");
        }

        return null;
    }
}
