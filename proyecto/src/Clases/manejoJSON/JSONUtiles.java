package Clases.manejoJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


        while (clase != null && clase != Object.class) { //recorro la jerarquia de clases hasta llegar a Object
            Field[] campos = clase.getDeclaredFields();
            //obtengo todos los atributos declarados
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

    public static <T> T jsonObjectToObjeto(JSONObject json, Class<T> clase) throws JSONException, IllegalAccessException {
        T instancia = null;
        try {
            instancia = clase.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        while (clase != null && clase != Object.class) {
            Field[] campos = clase.getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true);
                String nombreCampo = campo.getName();

                if (!json.has(nombreCampo) || json.isNull(nombreCampo)) {
                    continue;
                }

                Class<?> tipoCampo = campo.getType();

                if (esTipoPrimitivo(tipoCampo)) {
                    Object valor = obtenerValorPrimitivo(json, nombreCampo, tipoCampo);
                    campo.set(instancia, valor);

                } else if (Collection.class.isAssignableFrom(tipoCampo)) {
                    // si el campo es una lista, necesitamos conocer el tipo de sus elementos
                    ParameterizedType tipoGenerico = (ParameterizedType) campo.getGenericType();
                    Class<?> tipoElemento = (Class<?>) tipoGenerico.getActualTypeArguments()[0];

                    JSONArray jsonArray = json.getJSONArray(nombreCampo);
                    List<Object> lista = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object valorJson = jsonArray.get(i);
                        if (esTipoPrimitivo(tipoElemento)) {
                            lista.add(valorJson);
                        } else {
                            lista.add(jsonObjectToObjeto((JSONObject) valorJson, tipoElemento));
                        }
                    }

                    campo.set(instancia, lista);

                } else {
                    // si es una clase compuesta
                    JSONObject subJson = json.getJSONObject(nombreCampo);
                    Object subObjeto = jsonObjectToObjeto(subJson, tipoCampo);
                    campo.set(instancia, subObjeto);
                }
            }

            clase = (Class<T>) clase.getSuperclass();
        }

        return instancia;
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
    private static Object obtenerValorPrimitivo(JSONObject json, String clave, Class<?> tipo) throws JSONException {
        if (tipo == int.class || tipo == Integer.class) {
            return json.getInt(clave);
        } else if (tipo == long.class || tipo == Long.class) {
            return json.getLong(clave);
        } else if (tipo == double.class || tipo == Double.class) {
            return json.getDouble(clave);
        } else if (tipo == boolean.class || tipo == Boolean.class) {
            return json.getBoolean(clave);
        } else if (tipo == String.class) {
            return json.getString(clave);
        } else {
            return json.get(clave); // fallback (puede fallar si no es compatible)
        }
    }
}
