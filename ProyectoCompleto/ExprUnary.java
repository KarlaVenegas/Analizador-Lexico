
public class ExprUnary extends Expression{
    final Token operator;
    final Expression right;

    ExprUnary(Token operator, Expression right) {
        this.operator = operator;
        this.right = right;
    }
    public String toString() {
        return "ExprUnary['" + operator + right + ']';
    }
    public Object solve(TablaSimbolos t){
        Object rightR = right.solve(t);
        switch (operator.lexema){
            case "!":
                if(rightR instanceof Boolean){
                    return !(Boolean)rightR;
                }
                else
                    throw new RuntimeException("No es posible realizar la negación. ");
            case "-":
                if(rightR instanceof Number){
                    return -1 * ((Number) rightR).doubleValue();
                }
                else
                    throw new RuntimeException("No es posible realizar la multiplicación por -1. ");


        }

        return null;
    }
}
