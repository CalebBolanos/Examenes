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

import java.io.IOException;
import java.sql.ResultSet;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class procesarInicioSesion extends HttpServlet {

    HttpSession sesion;
    /**
     * Servlet encargado de autentificar los datos ingresados en el inicio de 
     * sesion y generar las sesiones tanto para el cliente como el administrador
     * con HttpSession
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * metodo encargado de autentificar los datos ingresados al inicio de sesion
     * haciendo una consulta a la base de datos, en caso de que los datos sean 
     * los correctos se genera la sesion en donde se guardan los datos de 
     * los usuarios y dependiendo del tipo de usuario se redirije a su apartado 
     * correspondiente
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        System.out.println(usuario);
        System.out.println(contrasena);
        boolean verificado = false;
        
        if (Validaciones.StringsNoVacios(usuario, contrasena)) {
            try {
                Conexion base = new Conexion();
                base.conectar();
                ResultSet rs = base.ejecutaQuery("call spIniciarSesion(\""+usuario+"\", \""+contrasena+"\");");
                if (rs.next()) {
                    if (rs.getString("msj").equals("ok")) {
                        verificado = true;
                        int id = rs.getInt("id");
                        int tipo = rs.getInt("tipo");
                        String nombre = rs.getString("nom");
                        String paterno = rs.getString("pat");
                        String materno = rs.getString("mat");
                        String correo = rs.getString("corr");

                        sesion = request.getSession();
                        sesion.setAttribute("id", id);
                        sesion.setAttribute("tipo", tipo);
                        sesion.setAttribute("nombre", nombre);
                        sesion.setAttribute("paterno", paterno);
                        sesion.setAttribute("materno", materno);
                        sesion.setAttribute("correo", correo);
                    } 
                }
                base.cierraConexion();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            
            if (verificado) {
                int tipoUsuario = (int)sesion.getAttribute("tipo");
                switch (tipoUsuario){
                    case 1:
                        response.sendRedirect("inicioCliente");
                        return;
                    case 2:
                        response.sendRedirect("inicioAdmin");
                        
                }
            } else {
                response.sendRedirect("iniciarSesion");
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
