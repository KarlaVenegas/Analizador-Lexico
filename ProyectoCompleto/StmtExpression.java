package mx.ipn.escom.k.parser;

public class StmtExpression extends Statement {
    final Expression expression;

    StmtExpression(Expression expression) {
        this.expression = expression;
    }
    public String toString() {
        return "StmtExpression['" + expression + ']';
    }
    public Object exect(TablaSimbolos t){
        return expression.solve(t);

    }
}
