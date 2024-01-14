
public class ExprGrouping extends Expression {
    final Expression expression;

    ExprGrouping(Expression expression) {
        this.expression = expression;
    }
    public String toString() {
        return "ExprGrouping['" + expression + ']';
    }
    public Object solve(TablaSimbolos t){
        return expression.solve(t);
    }
}
