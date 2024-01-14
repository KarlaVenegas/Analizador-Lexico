import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  ASDR2 implements Parser{
    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    //contador para ver si hubo algún error
    private int cont = 0;

    public ASDR2(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public ArrayList<Statement> parse() {
        ArrayList<Statement> statements = new ArrayList<>();


            PROGRAM(statements);
            if(preanalisis.tipo == TipoToken.EOF && cont == 0){
                System.out.println("Análisis sintáctico exitoso.");

                return statements;
            } else {
                System.out.println("Error en el análisis sintáctico. ");
                return statements;
            }

            /*
            if (preanalisis.tipo == TipoToken.EOF && !hayErrores) {

                System.out.println("Consulta correcta");
                return true;
            } else {
                System.out.println("Se encontraron errores");
            }*/


    }

    // PROGRAM -> DECLARATION
    private void PROGRAM(ArrayList<Statement> statements){        
       DECLARATION(statements);
    }

    private void DECLARATION(ArrayList<Statement> statements){
        if(preanalisis.tipo == TipoToken.FUN){
            statements.add(FUN_DECL()); //agregar lo que cache
            DECLARATION( statements);
        }
        else if(preanalisis.tipo == TipoToken.VAR){
            statements.add(VAR_DECL());
            DECLARATION(statements);
        }
        else if(preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE){
            statements.add(STATEMENT());
            DECLARATION(statements);
        }
    }

    


    private void match(TipoToken tt){
        if(preanalisis.tipo == tt){
            i++;
            preanalisis = tokens.get(i);
        }

        else{
            hayErrores = true;
            cont++;
            System.out.println("Error encontrado en línea " +preanalisis.fil);
            hayErrores= false;
        }

    }

    private Token previous() {
        return this.tokens.get(i - 1);
    }


    
}
