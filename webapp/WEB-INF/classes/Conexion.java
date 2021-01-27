/**
 * Bolaños Ramos Caleb Salomon 
 * García Marciano Edgar
 * Hernández Oble Axel
 * Olay Silis Jose Eduardo
 * Proyecto final de Programación Orientada a Objetos
 * Proyecto Aplicador y evaluador de examenes de opcion multiple
 * Miercoles 26 de enero de 2021 
 * 2CM3 
 * Programación Orientada a Objetos
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de establecer las conexiones y ejecutar consultas a 
 * la base de datos del proyecto
 */
public class Conexion {
    private String usrBD;
    private String passBD;
    private String urlBD;
    private String driverClassName;
    private Connection conn = null;
    private Statement sentencia;

    public Conexion() {
        usrBD = "root";
        passBD = "n0m3l0s3";
        urlBD = "jdbc:mysql://localhost:3306/examenes?serverTimezone=UTC";
        driverClassName = "com.mysql.cj.jdbc.Driver";
    }
 
    public Conexion(String usrBD, String passBD, String urlBD, String driverClassName) {
        this.usrBD = usrBD;
        this.passBD = passBD;
        this.urlBD = urlBD;
        this.driverClassName = driverClassName;
    }
    
    public void conectar() throws SQLException {
        try {
            Class.forName(driverClassName).newInstance();
            conn = DriverManager.getConnection(urlBD, usrBD, passBD);
 
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException err) {
            System.out.println("Error " + err.getMessage());
        }
    }
    
    public void cierraConexion() throws SQLException {
        conn.close();
    }
    
    public boolean ejecuta(String consulta) throws SQLException {
        sentencia = (Statement) conn.createStatement();
        return sentencia.execute(consulta);
    }
    
    public ResultSet ejecutaQuery(String consulta) throws SQLException {
        sentencia = (Statement) conn.createStatement();
        return sentencia.executeQuery(consulta);
    }
    
    public int ejecutaUpdate(String consulta) throws SQLException {
        sentencia = (Statement) conn.createStatement();
        return sentencia.executeUpdate(consulta);
    }
    
    public Connection getConexion(){
        return conn;
    }
}

