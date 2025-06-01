package Clases.manejoJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collection;

public class JSONUtiles {
    public static void grabar(JSONArray array, String archivo) {
        try {
            FileWriter file = new FileWriter(archivo);
            file.write(array.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public static JSONTokener leer(String archivo) {
        JSONTokener tokener = null;

        //chequeo si el archivo existe
        if(existeArchivo(archivo)) {
            try {
                tokener = new JSONTokener(new FileReader(archivo));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return tokener;
    }

    public static boolean existeArchivo(String archivo){
        File file = new File(archivo);
        return file.exists();
    }

    /// USO DE REFLEXION
    public static <T> JSONObject objetoToJSONOBJECT(T objeto) throws JSONException, IllegalAccessException {
        JSONObject json = new JSONObject();
        //obtengo la clase del objeto
        Class<?> clase = objeto.getClass();

        //obtengo todos los atributos declarados

        while (clase != null && clase != Object.class) { //recorro la jerarquia de clases hasta llegar a Object
            Field[] campos = clase.getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true); //hago accesible el atributo (incluso si es private)
                Object valor = campo.get(objeto);//obtengo el valor del campo

                if (valor == null) {
                    json.putOpt(campo.getName(), JSONObject.NULL);
                } else if (valor instanceof Collection<?> lista) { //chequeo si es una lista (para el historial de los usuarios)
                    JSONArray jsonArray = new JSONArray();

                    for (Object i : lista) {
                        if (esTipoPrimitivo(i.getClass())) { // si es objeto primitivo
                            jsonArray.put(i);
                        } else {
                            jsonArray.put(objetoToJSONOBJECT(i)); //uso recursividad en caso de que sea una clase personalizada
                        }
                    }

                    json.put(campo.getName(), jsonArray);
                } else if (esTipoPrimitivo(valor.getClass())) {
                    json.put(campo.getName(), valor);
                } else {
                    json.put(campo.getName(), objetoToJSONOBJECT(valor));
                }
            }
            clase = clase.getSuperclass(); //subo un nivel en la jerarquia
        }
            return json;
        }


    //para chequear si un objeto es tipo primitivo
    private static boolean esTipoPrimitivo (Class < ? > tipo) {
        return tipo.isPrimitive()
                || tipo == String.class
                || tipo == Integer.class
                || tipo == Double.class
                || tipo == Boolean.class
                || tipo == Long.class
                || tipo == Float.class
                || tipo == Short.class
                || tipo == Byte.class
                || tipo == Character.class;
    }
}
