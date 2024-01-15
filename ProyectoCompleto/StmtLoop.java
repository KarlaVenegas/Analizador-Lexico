

public class StmtLoop extends Statement {
    final Expression condition;
    final Statement body;

    StmtLoop(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }
    public String toString() {
        return "StmtLoop['" + "condition: " +condition + "body: "+ body + ']';
    }
    public Object exect(TablaSimbolos t){

        while ((Boolean) condition.solve(t)){
            body.exect(t);
        }

        return null;

    }
}
