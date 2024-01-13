public class Token {

    final TipoToken tipo;
    final String lexema;
    final Object literal;

    final int fil;
    
    

    public Token(TipoToken tipo, String lexema, int fil) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
        this.fil = fil;
    }

    public Token(TipoToken tipo, String lexema, Object literal, int fil) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.fil = fil;
    }

    public String toString() {
        return "<" + tipo + " " + lexema + " " + literal + " " + fil + ">";
    }


}
