package Clases.manejoJSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class JSONUtiles {
    public static void grabar(JSONObject array, String archivo) {
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

        //chequea si el archivo existe
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

}
