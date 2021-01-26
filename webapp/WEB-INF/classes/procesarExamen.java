/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author calebbolanos
 */
public class procesarExamen extends HttpServlet {

    HttpSession sesion;
    Conexion base;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sesion = request.getSession();
        if (sesion.getAttribute("id") == null || sesion.getAttribute("tipo") == null) {
            response.sendRedirect("iniciarSesion?respuesta=Sesion expirada. Vuelve a iniciar sesion");
            return;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
