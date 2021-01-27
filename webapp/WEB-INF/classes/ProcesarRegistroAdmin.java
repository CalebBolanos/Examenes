
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

public class ProcesarRegistroAdmin extends HttpServlet {

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
        String usuario = request.getParameter("nombreAdmin");
        String usuariop = request.getParameter("paternoAdmin");
        String usuariom = request.getParameter("maternoAdmin");
        String usuarioc = request.getParameter("correoAdmin");
        String usuariocont = request.getParameter("contrasenaAdmin");
        String a = "a";

        System.out.println(usuario);
        System.out.println(usuariop);
        System.out.println(usuariom);
        System.out.println(usuarioc);
        System.out.println(usuariocont);
        /*boolean verificado = false;*/

        if (Validaciones.StringsNoVacios(usuario, usuariop, usuariom, usuarioc, usuariocont)) {
            try {
                Conexion base = new Conexion();
                base.conectar();
                ResultSet rs = base.ejecutaQuery("call spRegistrarCliente(\"" + usuario + "\", \"" + usuariop + "\", \"" + usuariom + "\", \"" + usuarioc + "\", \"" + usuariocont + "\",\"" + a + "\");");
                if (rs.next()) {
                    if (rs.getString("msj1").equals("Se agrego nuevo Admin")) {

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
