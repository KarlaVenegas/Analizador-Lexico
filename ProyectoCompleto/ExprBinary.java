public class ExprBinary extends Expression{
    final Expression left;
    final Token operator;
    final Expression right;

    ExprBinary(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    public String toString() {
        return "ExprBinary['" + left + operator + right + ']';
    }
    public Object solve(TablaSimbolos t){
        Object leftR = left.solve(t);
        Object rightR = right.solve(t);
            switch (operator.lexema){
                case "+":
                    if(leftR instanceof Number && rightR instanceof Number){
                        return ((Number) leftR).doubleValue() + ((Number) rightR).doubleValue();
                    }
                    else if(leftR instanceof String && rightR instanceof String){
                        return leftR.toString() + rightR.toString();
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la operación suma. ");
                    }
                case "-":
                    if(leftR instanceof Number && rightR instanceof Number){
                        return ((Number) leftR).doubleValue() - ((Number) rightR).doubleValue();
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la operación resta. ");
                    }
                case "*":
                    if(leftR instanceof Number && rightR instanceof Number){

                        return ((Number) leftR).doubleValue() * ((Number) rightR).doubleValue();
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la operación multiplicación. ");
                    }
                case "/":
                    if(leftR instanceof Number && rightR instanceof Number){
                        if(((Number) rightR).doubleValue() == 0.0){
                            throw new RuntimeException("No es posible realizar la operación división (división por cero). ");
                        }
                        else{
                            return ((Number) leftR).doubleValue() / ((Number) rightR).doubleValue();
                        }
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la operación división. ");
                    }
                case "<":
                    if(leftR instanceof Number && rightR instanceof Number){
                        return ((Number) leftR).doubleValue() < ((Number) rightR).doubleValue();
                    }
                    else if(leftR instanceof String && rightR instanceof String){
                        return ((String) leftR).compareTo((String) rightR) < 0;
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la comparación.");
                    }
                case ">":
                    if(leftR instanceof Number && rightR instanceof Number){
                        return ((Number) leftR).doubleValue() > ((Number) rightR).doubleValue();
                    }
                    else if(leftR instanceof String && rightR instanceof String){
                        return ((String) leftR).compareTo((String) rightR) > 0;
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la comparación.");
                    }

                case "<=":
                    if(leftR instanceof Number && rightR instanceof Number){
                        return ((Number) leftR).doubleValue() <= ((Number) rightR).doubleValue();
                    }
                    else if(leftR instanceof String && rightR instanceof String){
                        return ((String) leftR).compareTo((String) rightR) <= 0;
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la comparación.");
                    }
                case ">=":
                    if(leftR instanceof Number && rightR instanceof Number){
                        return ((Number) leftR).doubleValue() >= ((Number) rightR).doubleValue();
                    }
                    else if(leftR instanceof String && rightR instanceof String){
                        return ((String) leftR).compareTo((String) rightR) >= 0;
                    }
                    else{
                        throw new RuntimeException("No es posible realizar la comparación.");
                    }
            }




        return null;
    }

}
