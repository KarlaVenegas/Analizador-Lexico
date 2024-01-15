
class ExprLiteral extends Expression {
    final Object value;
    final TipoToken tipo;
    final String lexema;

    ExprLiteral(Object value, TipoToken tipo, String lexema) {
        this.value = value;
        this.tipo = tipo;
        this.lexema = lexema;
    }
    public String toString() {
        return "ExprLiteral[" + value + ']';
    }

    public Object solve(TablaSimbolos t){
        if(value instanceof Number){
            return ((Number)value).doubleValue();
        }
        return value;
    }
}
