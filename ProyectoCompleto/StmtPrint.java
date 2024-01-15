package mx.ipn.escom.k.parser;

public class StmtPrint extends Statement {
    final Expression expression;

    StmtPrint(Expression expression) {
        this.expression = expression;
    }
        public String toString() {
        return "StmtPrint['" + expression + ']';
    }
    public Object exect(TablaSimbolos t){
        System.out.println(expression.solve(t));
        return null;
    }
}
