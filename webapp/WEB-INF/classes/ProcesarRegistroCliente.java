
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

public class ProcesarRegistroCliente extends HttpServlet {

    private Conexion base;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String usuario = request.getParameter("nombre");
        String usuariop = request.getParameter("paterno");
        String usuariom = request.getParameter("materno");
        String usuarioc = request.getParameter("correo");
        String usuariocont = request.getParameter("contrasena");
        String c = "c";

        System.out.println(usuario);
        System.out.println(usuariop);
        System.out.println(usuariom);
        System.out.println(usuarioc);
        System.out.println(usuariocont);

        if (Validaciones.StringsNoVacios(usuario, usuariop, usuariom, usuarioc, usuariocont)) {
            try {
                Conexion base = new Conexion();
                base.conectar();
                ResultSet rs = base.ejecutaQuery("call spRegistrarCliente(\"" + usuario + "\", \"" + usuariop + "\", \"" + usuariom + "\", \"" + usuarioc + "\", \"" + usuariocont + "\",\"" + c + "\");");
                if (rs.next()) {
                    if (rs.getString("msj3").equals("Se agrego nuevo usuario")) {
                        response.sendRedirect("registroUsuarios");
                    }
                    base.cierraConexion();
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        } else {
            System.out.println("Porfavor no dejes espacios en blanco.");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
