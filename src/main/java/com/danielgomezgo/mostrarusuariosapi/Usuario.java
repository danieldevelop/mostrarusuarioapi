package com.danielgomezgo.mostrarusuariosapi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * El constructor new URL(), está obsoleto de la versión 20 y versiones posteriores. Sin embargo, desde Java 11
 * se recomienda usar [java.net.http.HttpClient] en lugar de las antiguas clases de conexion HTTP. Si necesitas
 * construir una URL, puedes seguir usando URL, pero ten en cuenta que algunas de sus funcionalidades pueden estar
 * limitadas o marcadas como obsoletas en futuras versiones de Java.
 *
 * Con constructor new URL(), puedes construir metodo que retorne un String, de la siguiente manera:
 *
 * URL url = new URL("https://jsonplaceholder.typicode.com/users");
 * HttpURLConnection connect = (HttpURLConnection) urlAPI.openConnection();
 * connect.setRequestMethod("GET");
 *
 * int response = connect.getResponseCode();
 *
 * if (response == HttpURLConnection.HTTP_OK) {
 *      return connect
 *
 *      // try (InputStream is = connect.getInputStream()) {
 *      //    return new String(is.readAllBytes());
 *      // }
 * } else {
 *      JOptionPane.showMessageDialog(null, String.valueOf(response), "Error REST API", JOptionPane.ERROR_MESSAGE);
 *      throw new RuntimeException(String.valueOf(response));
 * }
 *
 * ------------------------------------------------------------------------------
 *
 * Para peticiones HTTP modernas lo ideal es:
 *
 * HttpClient client = HttpClient.newHttpClient();
 * HttpRequest request = HttpRequest.newBuilder()
 *     .uri(URI.create(Desc.API_URL_JSONPLACEHOLDER))
 *     .GET()
 *     .build();
 * HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
 */

public class Usuario {
    
    public void getUserAll(String[] column, Object[][] row, JTable tblUsuario) {
        try {
            URL url = new URL(Desc.URL_API + "users");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            
            int response = connect.getResponseCode();
            
            if (response != 200) {
                JOptionPane.showMessageDialog(null, "ERROR: No se pudo conectar a la REST API", "Mostrar Usuarios API", JOptionPane.ERROR_MESSAGE);
            } else {
                StringBuilder data = new StringBuilder();
                Scanner cin = new Scanner(url.openStream());
                
                while (cin.hasNext()) {
                    data.append(cin.nextLine());
                }
                cin.close();
                
                JSONArray dataUsers = new JSONArray(data.toString());
                ArrayList<Object[]> rows = new ArrayList<>();
                
                for (int i = 0; i < dataUsers.length(); i++) {
                    JSONObject user = dataUsers.getJSONObject(i);
                    
                    Object[] rowData = {
                        user.getInt("id"),
                        user.getString("name"),
                        user.getString("email"),
                        user.getString("phone")
                    };
                    
                    rows.add(rowData);
                }
                
                row = new Object[rows.size()][];
                rows.toArray(row);
                tblUsuario.setModel(new DefaultTableModel(row, column));
            }
            
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mostrar Usuarios API", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mostrar Usuarios API", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
