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

public class procesarExamen extends HttpServlet {

    HttpSession sesion;
    Conexion base;

    /**
     * Servlet encargado de guardar la respuesta de un reactivo a la base de datos y 
     * de calificar las respuestas de un examen
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sesion = request.getSession();
        if (sesion.getAttribute("id") == null || sesion.getAttribute("tipo") == null) {
            response.sendRedirect("iniciarSesion?respuesta=Sesion expirada. Vuelve a iniciar sesion");
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Dontro de doPosr se escribio la logica para ir redirigiendo la peticion 
     * del usuario en caso de que se quieran guardar reactivos o 
     * calificar un examen
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        base = new Conexion();
        int tipoTransaccion = Integer.parseInt(request.getParameter("tipoTransaccion"));
        int idExamen = Integer.parseInt(request.getParameter("idExamen"));
        int posicionPregunta = Integer.parseInt(request.getParameter("posicionPregunta"));
        System.out.println("ps guardada: " + posicionPregunta);
        switch (tipoTransaccion) {
            case 1://para guardar reactivos
                String respuesta = new String(request.getParameter("opciones").getBytes(), "UTF-8");
                int idReactivo = Integer.parseInt(request.getParameter("idReactivo"));
                ponerRespuesta(idReactivo, idExamen, respuesta, posicionPregunta);
                if (posicionPregunta == 10) {
                    calificarExamen(posicionPregunta, idExamen);
                    response.sendRedirect("inicioCliente");
                } else {
                    try (PrintWriter out = response.getWriter()) {
                        out.print(regresar(idExamen));
                    }
                }
                break;
            case 2://para calificar examen
                calificarExamen(posicionPregunta, idExamen);
                break;
        }
    }
    
    /**
     * metodo encargado de hacer la conexion a la base de datos para guardar 
     * la respuesta de un reactivo
     */
    public void ponerRespuesta(int idReactivo, int idExamen, String respuesta, int posicionPregunta) {
        try {
            base.conectar();
            ResultSet rs = base.ejecutaQuery("call Respues(" + sesion.getAttribute("id") + "," + idReactivo + ",\"" + respuesta + "\");");//"++"
            if (rs.next()) {

                if (rs.getString("msj").equals("ok")) {
                    System.out.println("guardado a la base");
                    ResultSet rsprogreso = base.ejecutaQuery("call AgreClien(" + sesion.getAttribute("id") + ", " + posicionPregunta + ", " + idExamen + ");");
                    if (rsprogreso.next()) {

                    }
                }
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * metodo encargado de calificar el examen del cliente y hacer la conexion 
     * a la base de datos para guardar dicha calificacion
     */
    public void calificarExamen(int posicionPregunta, int idExamen) {
        int calificacion = 0;
        try {
            base.conectar();
            base.ejecuta("SET sql_mode=(SELECT REPLACE(@@sql_mode,\"ONLY_FULL_GROUP_BY\",\"\"));");
            if (posicionPregunta < 10) {
                ResultSet rsprogreso = base.ejecutaQuery("call AgreClien(" + sesion.getAttribute("id") + ", " + 10 + ", " + idExamen + ");");//para actualizar estado de que ya está terminado
            }
            ResultSet rs = base.ejecutaQuery("select respuestaCorrecta, respuestaCliente from vwmostrarResultadosCompletos "
                    + "where idCliente = " + sesion.getAttribute("id") + " and idExamen = " + idExamen + ";");//
            while (rs.next()) {
                if (rs.getString("respuestaCorrecta").equals(rs.getString("respuestaCliente"))) {
                    calificacion++;
                }
            }
            ResultSet rsCalificacion = base.ejecutaQuery("call spPonerCalificacionExamen(" + idExamen + ", " + sesion.getAttribute("id") + ", " + calificacion + ");");
            if (rsCalificacion.next()) {
                if (rsCalificacion.getString("msj").equals("ok")) {
                    System.out.println("Calificacion guardada");
                }
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * metodo que genera codigo html en donde se encuentra un form 
     * para mandar el id del examen con el metodo POST 
     */
    public String regresar(int idExamen) {
        return ""
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>respuesta</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <script type=\"text/javascript\">\n"
                + "            window.onload = function() {\n"
                + "                document.getElementById(\"a\").submit();\n"
                + "            };\n"
                + "        </script>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <form method=\"POST\" action=\"aplicadorExamen\" id=\"a\">\n"
                + "            <input type=\"hidden\" name =\"idExamen\" value=\"" + idExamen + "\"/>\n"
                + "        </form>\n"
                + "    </body>\n"
                + "</html>";
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
