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

// FUN_DECL -> fun FUNCTION
    private Statement FUN_DECL(){
        if(preanalisis.tipo == TipoToken.FUN){
            match(TipoToken.FUN);
            Statement st = FUNCTION();
            return st;
        }
        return null;

    }

    // VAR_DECL -> var id VAR_INIT ;
    private Statement VAR_DECL(){
        match(TipoToken.VAR);
        match(TipoToken.IDENTIFIER);
        Token name = previous();
        Expression expr = VAR_INIT();
        match(TipoToken.SEMICOLON);
        return new StmtVar(name, expr);
    }

    //VAR_INIT -> = EXPRESSION
    //         -> Ɛ
    private Expression VAR_INIT(){
        Expression expr = null;
         if(preanalisis.tipo == TipoToken.EQUAL){
             match(TipoToken.EQUAL);
             expr = EXPRESSION();
             return expr;
         }
         return expr;
     }

    
//Aqui empiezan los Statement(Sentencias)
    
    //STATEMENT -> EXPR_STMT
    //-> FOR_STMT
    //-> IF_STMT
    //-> PRINT_STMT
    //-> RETURN_STMT
    //-> WHILE_STMT
    //-> BLOCK

    private Statement STATEMENT(){
        Statement st;
        
        if(preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.LEFT_PAREN){
            st = EXPR_STMT();
            return st;
        }
        else if(preanalisis.tipo == TipoToken.FOR){
            st = FOR_STMT();
            return st;
        }
        else if(preanalisis.tipo == TipoToken.IF){
            st = IF_STMT();
            return st;
        }
        else if(preanalisis.tipo == TipoToken.PRINT){
            st = PRINT_STMT();
            return st;
        }
        else if(preanalisis.tipo == TipoToken.RETURN){
            st = RETURN_STMT();
            return st;
        }
        else if(preanalisis.tipo == TipoToken.WHILE){
            st = WHILE_STMT();
            return st;
        }
        else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
            st = BLOCK();
            return st;
        }
        
        return null;
        
    }

    // EXPR_STMT -> EXPRESSION ;   
    private Statement EXPR_STMT(){
            Expression expr = EXPRESSION();
            match(TipoToken.SEMICOLON);
            Statement st = new StmtExpression(expr);
            return st;
    }

    // FOR_STMT
    private Statement FOR_STMT(){
            match(TipoToken.FOR);
            match(TipoToken.LEFT_PAREN);
            Statement ini = FOR_STMT_1();
            Expression condi = FOR_STMT_2();
            Expression incre = FOR_STMT_3();
            match(TipoToken.RIGHT_PAREN);
            Statement body = STATEMENT();

            if(incre != null){
                body = new StmtBlock(Arrays.asList(body, new StmtExpression(incre)));
            }

            if(condi == null){
                condi = new ExprLiteral(true, TipoToken.BOOL, "BOOL");
            }
            body = new StmtLoop(condi, body);

            if(ini != null){
                body = new StmtBlock(Arrays.asList(ini, body));
            }

            return body;
    }

    //FOR_STMT_1
    private Statement FOR_STMT_1(){

        
        if(preanalisis.tipo == TipoToken.VAR){
            Statement st = VAR_DECL();
            return  st;
        }
        else if(preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.LEFT_PAREN ){
            Statement st = EXPR_STMT();
        }
        else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
            return null;

        }
        return null;

        
    }

   // FOR_STMT_2 
    private Expression FOR_STMT_2(){
        if(preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.LEFT_PAREN){
            Expression expr = EXPRESSION();
            match(TipoToken.SEMICOLON);
            return expr;
        }
        else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
            return null;
        }
        return null;
    }
    //FOR_STMT_3
    private Expression FOR_STMT_3(){
     Expression expr = null;

     if(preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.LEFT_PAREN ){
         expr = EXPRESSION();
     }
     return expr;
    }

    //IF_STMT 
    private Statement IF_STMT(){

            match(TipoToken.IF);
            match(TipoToken.LEFT_PAREN);
            Expression expr = EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            Statement st = STATEMENT();
            Statement st2 = ELSE_STATEMENT();
            return new StmtIf(expr, st, st2);


    }

    //ELSE_STATEMENT -> else STATEMENT
    private Statement ELSE_STATEMENT(){
        if(preanalisis.tipo == TipoToken.ELSE){
            match(TipoToken.ELSE);
            Statement st = STATEMENT();
            return st;
        }
        return null;
    }

    // PRINT_STMT -> print EXPRESSION ;
    private Statement PRINT_STMT(){
        match(TipoToken.PRINT);
        Expression expr = EXPRESSION();
        match(TipoToken.SEMICOLON);
        return new StmtPrint(expr);
    }
    
    // RETURN_STMT -> return RETURN_EXP_OPC ;
    private Statement RETURN_STMT(){
        match(TipoToken.RETURN);
        Expression expr = RETURN_EXP_OPC();
        match(TipoToken.SEMICOLON);
        return new StmtReturn(expr);
    }
    // RETURN_EXP_OPC -> EXPRESSION
    private Expression RETURN_EXP_OPC(){
        Expression expr = null;

        if(preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.LEFT_PAREN ){
            expr = EXPRESSION();
        }
        return expr;
    }

    // WHILE_STMT -> while ( EXPRESSION ) STATEMENT
    private Statement WHILE_STMT(){

            match(TipoToken.WHILE);
            match(TipoToken.LEFT_PAREN);
            Expression condi = EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            Statement body = STATEMENT();
            return new StmtLoop(condi, body);

    }

    // BLOCK -> { DECLARATION }
    private StmtBlock BLOCK(){

            match(TipoToken.LEFT_BRACE);
            ArrayList<Statement> decl = new ArrayList<>();
            DECLARATION(decl);
            match(TipoToken.RIGHT_BRACE);
            return new StmtBlock(decl);


    }
    //Aqui empiezan las expresiones
    
    // EXPRESSION -> ASSIGNMENT
    private Expression EXPRESSION(){
        Expression expr = ASSIGNMENT();
        return expr;

    }

    // ASSIGNMENT -> LOGIC_OR ASSIGNMENT_OP
    private Expression ASSIGNMENT(){
        Expression expr = LOGIC_OR();
        expr = ASSIGNMENT_OPC(expr);
        return expr;


    }

   // ASSIGNMENT_OPC -> = EXPRESSION    ExprAssign
//                  -> Ɛ
    private Expression ASSIGNMENT_OPC(Expression expr){

        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            Expression value = EXPRESSION();

            if(expr instanceof ExprVariable){
                Token name = ((ExprVariable) expr).name;
                return new ExprAssign(name, value);
            }
        }
        return expr;
    }

    
    //LOGIC_OR -> LOGIC_AND LOGIC_OR_2
    private Expression LOGIC_OR(){
        Expression expr = LOGIC_AND();
        expr = LOGIC_OR_2(expr);
        return expr;

    }

 //   LOGIC_OR_2 -> or LOGIC_AND LOGIC_OR_2
//                  -> Ɛ
    private Expression LOGIC_OR_2(Expression expr){
        if(preanalisis.tipo == TipoToken.OR){
            match(TipoToken.OR);
            Token operador = previous();
            Expression expr2 = LOGIC_AND();
            ExprLogical expl = new ExprLogical(expr, operador, expr2);
            return LOGIC_OR_2(expl);
        }
        return expr;
    }
// LOGIC_AND -> EQUALITY LOGIC_AND_2
    private Expression LOGIC_AND(){
        Expression expr = EQUALITY();
        expr = LOGIC_AND_2(expr);
        return expr;

    }

 //   LOGIC_AND_2 -> and EQUALITY LOGIC_AND_2
 //                -> Ɛ
    private Expression LOGIC_AND_2(Expression expr){
        if(preanalisis.tipo == TipoToken.AND){
            match(TipoToken.AND);
            Token operador = previous();
            Expression expr2 = EQUALITY();
            ExprLogical expl = new ExprLogical(expr, operador, expr2);
            return LOGIC_AND_2(expl);
        }
        return expr;
    }

    // EQUALITY -> COMPARISON EQUALITY_2
    private Expression EQUALITY(){
        Expression expr = COMPARASION();
        expr = EQUALITY_2(expr);
        return expr;

    }

    // EQUALITY_2 -> != COMPARISON EQUALITY_2   exprLogical
//             -> == COMPARISON EQUALITY_2
//              -> Ɛ

    private Expression EQUALITY_2(Expression expr){
        if(preanalisis.tipo == TipoToken.BANG_EQUAL){
            match(TipoToken.BANG_EQUAL);
            Token operador = previous();
            Expression expr2 = COMPARASION();
            ExprLogical expl = new ExprLogical(expr, operador, expr2);
            return EQUALITY_2(expl);
        }
        else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
            match(TipoToken.EQUAL_EQUAL);
            Token operador = previous();
            Expression expr2 = COMPARASION();
            ExprLogical expl = new ExprLogical(expr, operador, expr2);
            return EQUALITY_2(expl);
        }
        return expr;
    }
// COMPARISON -> TERM COMPARISON_2

    private Expression COMPARASION(){
        Expression expr = TERM();
        expr = COMPARASION_2(expr);
        return expr;

    }

//    COMPARISON_2 -> > TERM COMPARISON_2         ExprLogical
//                  -> >= TERM COMPARISON_2
//                  -> < TERM COMPARISON_2
//                  -> <= TERM COMPARISON_2
//                  -> Ɛ
    private Expression COMPARASION_2(Expression expr){

        if(preanalisis.tipo == TipoToken.GREATER) {
            match(TipoToken.GREATER);
            Token operador = previous();
            Expression expr2 = TERM();
            ExprBinary expl = new ExprBinary(expr, operador, expr2);
            return COMPARASION_2(expl);
        }
        else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
            match(TipoToken.GREATER_EQUAL);
            Token operador = previous();
            Expression expr2 = TERM();
            ExprBinary expl = new ExprBinary(expr, operador, expr2);
            return COMPARASION_2(expl);
        }
        else if(preanalisis.tipo == TipoToken.LESS){
            match(TipoToken.LESS);
            Token operador = previous();
            Expression expr2 = TERM();
            ExprBinary expl = new ExprBinary(expr, operador, expr2);
            return COMPARASION_2(expl);
        }
        else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
            match(TipoToken.LESS_EQUAL);
            Token operador = previous();
            Expression expr2 = TERM();
            ExprBinary expl = new ExprBinary(expr, operador, expr2);
            return COMPARASION_2(expl);
        }
        return expr;
    }

    // TERM -> FACTOR TERM_2
    private Expression TERM(){
        Expression expr = FACTOR();
        expr = TERM_2(expr);
        return expr;

    }

//---------------------------------
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
