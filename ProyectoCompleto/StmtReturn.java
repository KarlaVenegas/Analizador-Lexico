

public class StmtReturn extends Statement {
    final Expression value;

    StmtReturn(Expression value) {
        this.value = value;
    }
        public String toString() {
        return "StmtReturn['" + value +  ']';
    }
    public Object exect(TablaSimbolos t){
         return value.solve(t);


    }
}
