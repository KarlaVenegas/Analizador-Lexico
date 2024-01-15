

public abstract class Statement {
    TablaSimbolos j;

    public Statement() {
        j = new TablaSimbolos();

    }

    public TablaSimbolos getTablaHash() {
        return j;
    }


    public abstract Object exect(TablaSimbolos t);
}
