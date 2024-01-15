package mx.ipn.escom.k.parser;

public class StmtIf extends Statement {
    final Expression condition;
    final Statement thenBranch;
    final Statement elseBranch;

    StmtIf(Expression condition, Statement thenBranch, Statement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    public String toString() {
        return "StmtIf['" + condition + thenBranch + elseBranch + ']';
    }
    public Object exect(TablaSimbolos t){
        if(condition.solve(t) instanceof Boolean){
            if((Boolean) condition.solve(t)){

                thenBranch.exect(t);
            }
            else{
                if(elseBranch!=null){
                    elseBranch.exect(t);
                }

            }




        }
        else{
            throw new RuntimeException("Condición inválida");
        }
        return null;


    }
}
