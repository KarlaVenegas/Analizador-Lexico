import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Interprete {

    static boolean existenErrores = false;

    public static void main(String[] args) throws IOException {
        if(args.length > 1) {
            System.out.println("Uso correcto: interprete [archivo.txt]");

            // Convención defininida en el archivo "system.h" de UNIX
            System.exit(64);
        } else if(args.length == 1){
            ejecutarArchivo(args[0]);
        } else{
            ejecutarPrompt();
        }
    }

    private static void ejecutarArchivo(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        ejecutar(new String(bytes, Charset.defaultCharset()));

        // Se indica que existe un error
        if(existenErrores) System.exit(65);
    }

    private static void ejecutarPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print(">>> ");
            String linea = reader.readLine();
            if(linea == null) break; //Presionar Ctrl + D
            ejecutar(linea);
            existenErrores = false;
        }
    }
        
    

    private static void ejecutar(String source) {

        try{

            Scanner scanner = new Scanner(source);
            List<Token> tokens = scanner.scan();
            
            for(Token token : tokens){
                System.out.println(token);
            } //Comentado para el ASDR

            Parser parser = new ASDR2(tokens);
            List<Statement> st = parser.parse();
            

            AnalizadorSemantico an = new AnalizadorSemantico(st);
            an.proceso();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /*
    El método error se puede usar desde las distintas clases
    para reportar los errores:
    Interprete.error(....);
     */
    static void error1(int line, char error){
        reportar1(line, "caracter no perteneciente al lenguaje", error);
    }

    private static void reportar1(int linea, String mensaje, char error){
        System.err.println(
                "[linea " + linea + "] Error " + mensaje + ": " + error);
        existenErrores = true;
    }

    //Se esperaba un digito
    static void error2(int linea){
        reportar2(linea, "se esperaba un digito");
    }

    private static void reportar2(int linea, String mensaje){
        System.err.println(
                "[linea " + linea + "] Error " + mensaje);
        existenErrores = true;
    }

    static void error(int linea, String mensaje){
        reportar(linea, "", mensaje);
    }

    private static void reportar(int linea, String posicion, String mensaje){
        System.err.println(
                "[linea " + linea + "] Error jj" + posicion + ": " + mensaje
        );
        existenErrores = true;
    }

}