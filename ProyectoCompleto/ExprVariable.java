
class ExprVariable extends Expression {
    final Token name;

    ExprVariable(Token name) {
        this.name = name;
    }
    public String toString() {
        return "ExprVariable['" + name + ']';
    }
    public Object solve(TablaSimbolos t){

            return t.obtener(name.lexema);


    }
}