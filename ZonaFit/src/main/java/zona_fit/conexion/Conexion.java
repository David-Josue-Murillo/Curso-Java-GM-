package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion() {
        Connection conexion = null;

        // Datos para conectarse a la base de datos
        String baseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "root";

        // Conexión a la base de datos
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password); // Conectarse a la base de datos
        }catch (Exception e) {
            System.out.println("Erro al conectarse a la base de datos: " + e.getMessage());
        }

        return conexion;
    }

    // Prueba para verificar si hay conexion
    public static void main(String[] args) {
        var conexion = Conexion.getConexion();

        // Verificar la conexion
        if(conexion != null) {
            System.out.println("Conexión exitosa");
        } else {
            System.out.println("Error al conectarse");
        }
    }
}
