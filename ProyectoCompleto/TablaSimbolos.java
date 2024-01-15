import java.util.HashMap;
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



    void asignar(String identificador, Object valor){
        values.put(identificador, valor);
    }


}