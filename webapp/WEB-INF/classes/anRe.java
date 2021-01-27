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

public class anRe extends HttpServlet {

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
        String pregunta = request.getParameter("pregunta");
        String opcionA = request.getParameter("opcionA");
        String opcionB = request.getParameter("opcionB");
        String opcionC = request.getParameter("opcionC");
        String opcionD = request.getParameter("opcionD");
        String respuesta = request.getParameter("respuesta");
        if (Validaciones.StringsNoVacios(pregunta, opcionA, opcionB, opcionC, opcionD, respuesta)) {
            try {
                Conexion base = new Conexion();
                base.conectar();
                ResultSet rs = base.ejecutaQuery("call CreaReac(\"" + pregunta + "\", \"" + opcionA + "\", \"" + opcionB + "\", \"" + opcionC + "\", \"" + opcionD + "\", \"" + respuesta + "\" );");
                if (rs.next()) {
                    if (rs.getString("msj").equals("Agregado")) {
                        response.sendRedirect("anadirReactivo");
                    }
                    base.cierraConexion();
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
