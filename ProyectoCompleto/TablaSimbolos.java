import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaSimbolos {

    private final Map<String, Object> values = new HashMap<>();

    boolean existeIdentificador(String identificador){
        return values.containsKey(identificador);
    }

    Object obtener(String identificador) {
        if (values.containsKey(identificador)) {
            return values.get(identificador);
        }
        throw new RuntimeException("Variable no definida '" + identificador + "'.");
    }

    void CopiarA(TablaSimbolos nueva){
        nueva.values.putAll(this.values);
    }

    void AsignaValoresDeOtraTabla(TablaSimbolos OtraTabla){
        for(Map.Entry<String, Object> aux: OtraTabla.values.entrySet()){
            String clave = aux.getKey();
            Object valor = aux.getValue();

            if(values.containsKey(clave)){
                values.put(clave, valor);
            }

        }

    }

    void AsignaStatements(TablaSimbolos OtraTabla){
        for(Map.Entry<String, Object> aux: OtraTabla.values.entrySet()){
            String clave = aux.getKey();
            Object valor = aux.getValue();

            if(valor instanceof StmtFunction){
                values.put(clave, valor);
            }
        }
    }

    int tamano(){
        return values.size();
    }

    void asignar(String identificador, Object valor){
        values.put(identificador, valor);
    }

    //Recorre una lista y asgina a una TablaSimbolos
    void recorrerAsigna(List<Object> params){
        for(Map.Entry<String, Object> entry : values.entrySet()){
            String clave = entry.getKey();
            values.put(clave, params.remove(params.size()-1));
        }
    }

    //Muestra tablaSimbolos
    void mostrar(){
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }
    }

    boolean sonValoresIguales() {
        // Obtener el primer valor del HashMap
        Object primerValor = null;
        String aux = null, aux2;



        for (Object valor : values.values()) {
            primerValor = valor;
            if(primerValor instanceof Number){
                aux = "NUMBER";
            }
            else if(primerValor instanceof String){
                aux= "STRING";
            }
            else
                aux = "a";

            break;
        }

        // Verificar si todos los valores son iguales al primer valor
        for (Object valor : values.values()) {
            if(valor instanceof Number){
                aux2 = "NUMBER";
            }
            else if(valor instanceof String){
                aux2= "STRING";
            }
            else
                aux2 = "a";

            if (aux!=aux2) {
                return false; // Se encontró un valor diferente
            }
        }

        return true; // Todos los valores son iguales
    }


}