package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conectar;

    public void Conexion() {
        try {
            if (conectar == null) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");                
                conectar = DriverManager.getConnection("jdbc:sqlserver://34.73.201.76;database=Team02", "User02", "User02-20199");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    public void cerrar() throws SQLException {      //Cerrar la conexion
        if (conectar != null) {
            if (conectar.isClosed() == false) {
                conectar.close();
                conectar = null;
            }
        }
    }

    public Connection getConectar() {
        return conectar;
    }

    public void setConectar(Connection conectar) {
        this.conectar = conectar;
    }

    public static void main(String[] args) {
        Conexion dao = new Conexion();
        dao.Conexion();
        if (dao.getConectar() != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("No hay Conexion");
        }
    }
}
