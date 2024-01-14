
public class ExprLogical extends Expression{
    final Expression left;
    final Token operator;
    final Expression right;

    ExprLogical(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    public String toString() {
        return "ExprLogical['" + left + operator + right + ']';
    }
    public Object solve(TablaSimbolos t){
        Object leftR = left.solve(t);
        Object rightR = right.solve(t);
        switch (operator.lexema) {
            case "==":
                if (leftR instanceof Number && rightR instanceof Number) {
                    return ((Number) leftR).doubleValue() == ((Number) rightR).doubleValue();
                } else if (leftR instanceof String && rightR instanceof String) {
                    return ((String) leftR).compareTo((String) rightR) == 0;
                } else if (leftR instanceof Boolean && rightR instanceof Boolean) {
                    return (Boolean) leftR == (Boolean) rightR;
                } else {
                    throw new RuntimeException("No es posible realizar la comparación. ");
                }



            case "!=":
                if (leftR instanceof Number && rightR instanceof Number) {
                    return ((Number) leftR).doubleValue() != ((Number) rightR).doubleValue();
                } else if (leftR instanceof String && rightR instanceof String) {
                    return leftR != rightR;
                } else if (leftR instanceof Boolean && rightR instanceof Boolean) {
                    return (Boolean) leftR != (Boolean) rightR;
                } else {
                    throw new RuntimeException("No es posible realizar la comparación. ");
                }
            case "and":
                if (leftR instanceof Boolean && rightR instanceof Boolean) {
                    return (Boolean) leftR && (Boolean) rightR;
                } else {
                    throw new RuntimeException("No es posible realizar la comparación. ");
                }
            case "or":
                if (leftR instanceof Boolean && rightR instanceof Boolean) {
                    return (Boolean) leftR || (Boolean) rightR;
                } else {
                    throw new RuntimeException("No es posible realizar la comparación. ");
                }


        }

        return null;
    }
}

