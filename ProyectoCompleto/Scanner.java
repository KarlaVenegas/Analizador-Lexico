import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;
    public static final Map<String, TipoToken> UnSimbolo;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);

        UnSimbolo = new HashMap<>();
        UnSimbolo.put("*",   TipoToken.STAR);
        UnSimbolo.put("{",   TipoToken.LEFT_BRACE);
        UnSimbolo.put("}",   TipoToken.RIGHT_BRACE);
        UnSimbolo.put(",",   TipoToken.COMMA);
        UnSimbolo.put("-",   TipoToken.MINUS);
        UnSimbolo.put(".",   TipoToken.DOT);
        UnSimbolo.put(";",   TipoToken.SEMICOLON);
        UnSimbolo.put("+",   TipoToken.PLUS);
        UnSimbolo.put("(",   TipoToken.LEFT_PAREN);
        UnSimbolo.put(")",   TipoToken.RIGHT_PAREN);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private final List<Character> UnSoloCarac = Arrays.asList('+', '-', '*', '{', '}', '(', ')', ',', '.', ';');
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        String lexema = "";
        int linea = 1;
        int estado = 0;
        char c;
        for(int i=0; i<source.length(); i++){
            c = source.charAt(i);

            if(i>=1){
                if(source.charAt(i - 1) == '\n'){
                    linea += 1;
                }
            }

            switch (estado){
                case 0:
                    if(Character.isLetter(c)){
                        estado = 13;
                        lexema += c;
                    }
                    else if(Character.isDigit(c)){
                        estado = 15;
                        lexema += c;
                    }
                    else if(c == '/'){
                        estado = 26;
                        lexema += c;
                    }
                    else if(c == '"'){
                        estado = 24;
                        lexema += c;
                    }
                    else if(c == '>'){
                        estado = 1;
                        lexema += c;
                    }
                    else if(c == '<'){
                        estado = 4;
                        lexema += c;
                    }
                    else if(c == '='){
                        estado = 7;
                        lexema += c;
                    }
                    else if(c == '!'){
                        estado = 10;
                        lexema += c;
                    }
                    else if(UnSoloCarac.contains(c)){
                        estado = 100;
                        lexema += c;
                    }
                    else if (c>32){
                        Interprete.error1(linea, c);
                        estado = -1;
                    }
                    break;
                
                case 1:
                    if(c == '='){
                        lexema += c;
                        Token t = new Token(TipoToken.GREATER_EQUAL, lexema, linea);
                        tokens.add(t);
                    }
                    else{
                        Token t = new Token(TipoToken.GREATER, lexema, linea);
                        tokens.add(t);
                        i--; //Regresa para así analizarlo en otro estado.
                    }
                    estado = 0;
                    lexema = "";
                    break;

                case 4:
                    if(c == '='){
                        lexema += c;
                        Token t = new Token(TipoToken.LESS_EQUAL, lexema, linea);
                        tokens.add(t);
                    }
                    else{
                        Token t = new Token(TipoToken.LESS, lexema, linea);
                        tokens.add(t);
                        i--;
                    }
                    estado = 0;
                    lexema = "";
                    break;
                
                case 7:
                    if(c == '='){
                        lexema += c;
                        Token t = new Token(TipoToken.EQUAL_EQUAL, lexema, linea);
                        tokens.add(t);
                    }
                    else{
                        Token t = new Token(TipoToken.EQUAL, lexema, linea);
                        tokens.add(t);
                        i--;
                    }

                    estado = 0;
                    lexema = "";
                    break;
                
                case 10:
                    if(c == '='){
                        lexema += c;
                        Token t = new Token(TipoToken.BANG_EQUAL, lexema, linea);
                        tokens.add(t);
                    }
                    else{
                        Token t = new Token(TipoToken.BANG, lexema, linea);
                        tokens.add(t);
                        i--;
                    }

                    estado = 0;
                    lexema = "";
                    break;
                
                case 13:
                    if(Character.isLetter(c) || Character.isDigit(c)){
                        lexema += c;
                    }
                    else{
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){
                            Token t = new Token(TipoToken.IDENTIFIER, lexema, linea);
                            tokens.add(t);
                        }
                        else{
                            Token t = new Token(tt, lexema, linea);
                            tokens.add(t);
                        }

                        estado = 0;
                        lexema = "";
                        i--;

                    }
                    break;

                case 15:
                    if(Character.isDigit(c)){
                        lexema += c;
                    }
                    else if(c == '.'){
                        estado = 16;
                        lexema += c;
                    }
                    else if(c == 'E'){
                        estado = 18;
                        lexema += c;
                    }
                    else{
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema), linea);
                        tokens.add(t); 

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                
                case 16:
                    if(Character.isDigit(c)){
                        estado = 17;
                        lexema += c;
                    }
                    else{
                        Interprete.error2(linea);
                        estado = -1;
                    }
                    break;
                
                case 17:
                    if(Character.isDigit(c)){
                        lexema += c;
                    }
                    else if(c == 'E'){
                        estado = 18;
                        lexema += c;
                    }
                    else {
                        Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema), linea); //Realiza la conversión
                        tokens.add(t); 

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                
                case 18:
                    if(c == '+' || c == '-'){
                        estado = 19;
                        lexema += c;
                    }
                    else if(Character.isDigit(c)){
                        estado = 20;
                        lexema += c;
                    }
                    else{
                        System.err.println("[linea " + linea + "] Error " + "se esperaba un simbolo '+', '-', o un digito");
                        estado = -1;
                    }
                    break;
                
                case 19:
                    if(Character.isDigit(c)){
                        estado = 20;
                        lexema += c;
                    }
                    else{
                        System.err.println("[linea " + linea + "] Error " + "se esperaba un digito");
                        estado = -1;
                    }
                    break;
                
                case 20:
                    if(Character.isDigit(c)){
                        lexema += c;
                    }
                    else{
                        Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema), linea);
                        tokens.add(t); 

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                
                case 24:
                    if(c == '\n' || i == source.length()-1){
                        
                        System.err.println("[linea " + linea + "] Error " + "se esperaba cierre de comillas dobles.");
                        estado = -1;
                        
                    }
                    else if (c == '"'){
                        lexema += c; 
                        Token t = new Token(TipoToken.STRING, lexema, String.valueOf(lexema.substring(1, lexema.length()-1)), linea);
                        tokens.add(t); //considerado edo. 25

                        estado = 0;
                        lexema = "";
                    }
                    else{ // Cualquier caracter
                        lexema += c;
                    }
                    break;

                case 26:
                    if(c == '*'){
                        estado = 27;
                    }
                    else if (c == '/') {
                        estado = 30;
                    }
                    else{
                        Token t = new Token(TipoToken.SLASH, lexema, linea);
                        tokens.add(t); 

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                
                case 27:
                    if(c == '*'){
                        estado = 28;
                    }
                    else if(i == source.length()-1){
                        System.err.println("[linea " + linea + "] Error " + "se esperaba cierre de comentario multilinea.");
                        estado = -1;
                    }
                    else{
                        estado = 27;
                    }
                    break;
                
                case 28:
                    if(c == '*'){
                        estado = 28;
                    }
                    else if (c == '/') {
                        estado = 0;
                        lexema = ""; 
                    }
                    else if(i == source.length()-1){
                        System.err.println("[linea " + linea + "] Error " + "se esperaba cierre de comentario multilinea.");
                        estado = -1;
                    }
                    else{
                        estado = 27;
                    }
                    break;
            
                case 30:
                    if(c == '\n' || i == source.length()-1){ // Si hay un salto de linea ó ya no hay mas líneas por analizar
                        estado = 0;
                        lexema = ""; 
                  
                    }
                    break;
                
                case 100:
                    TipoToken tt = UnSimbolo.get(lexema);
                    Token t = new Token(tt, lexema, linea);
                    tokens.add(t);

                    estado = 0;
                    lexema = "";
                    i--;
                    break;
                default:
                    lexema = "";
                    
                    break;
            
            }
        }
        tokens.add(new Token(TipoToken.EOF, "", linea));
        return tokens;
    }
}
