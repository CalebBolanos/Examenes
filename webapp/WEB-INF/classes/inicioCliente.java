/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author calebbolanos
 */
public class inicioCliente extends HttpServlet {

    ArrayList<Examen> examenes;
    HttpSession sesion;

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
        response.setContentType("text/html;charset=UTF-8");

        sesion = request.getSession();
        if (sesion.getAttribute("id") == null || sesion.getAttribute("tipo") == null) {
            response.sendRedirect("iniciarSesion?respuesta=Sesion expirada. Vuelve a iniciar sesion");
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            String htmlExamenes = obtenerExamenes();
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <title>Inicio</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://code.getmdl.io/1.3.0/material.blue-indigo.min.css\" />\n"
                    + "        <script defer src=\"https://code.getmdl.io/1.3.0/material.min.js\"></script>\n"
                    + "        <style>\n"
                    + "            .seleccionado{\n"
                    + "                background: linear-gradient(to right, #00B4DB, #2948ff);\n"
                    + "                font-size: 15px;\n"
                    + "                color: white !important;\n"
                    + "            }\n"
                    + "\n"
                    + "            .disponible{\n"
                    + "                font-size: 15px;\n"
                    + "                transition: all 0.3s;\n"
                    + "            }\n"
                    + "            .layout {\n"
                    + "                display: flex;\n"
                    + "                justify-content: center;\n"
                    + "                align-items: center;\n"
                    + "            }\n"
                    + "\n"
                    + "            .tarjeta {\n"
                    + "                height: auto;\n"
                    + "                padding: 20px 50px 20px 50px;\n"
                    + "                margin: 50px 50px 20px 50px;\n"
                    + "                box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);\n"
                    + "                transition: all 0.3s cubic-bezier(.25,.8,.25,1);\n"
                    + "            }\n"
                    + "\n"
                    + "            .tarjeta:hover {\n"
                    + "                box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"mdl-layout mdl-js-layout mdl-layout--fixed-header\">\n"
                    + "            <header class=\"mdl-layout__header\">\n"
                    + "                <div class=\"mdl-layout__header-row\">\n"
                    + "                    <span class=\"mdl-layout-title\">Inicio</span>\n"
                    + "                    <div class=\"mdl-layout-spacer\"></div>\n"
                    + "                    <nav class=\"mdl-navigation mdl-layout--large-screen-only\">\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"infoCliente\">" + sesion.getAttribute("nombre") + "</a>\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"cerrarSesion\">Cerrar Sesion</a>\n"
                    + "                    </nav>\n"
                    + "                </div>\n"
                    + "            </header>\n"
                    + "            <div class=\"mdl-layout__drawer\">\n"
                    + "                <span class=\"mdl-layout-title\">Examen</span>\n"
                    + "                <nav class=\"mdl-navigation\">\n"
                    + "                    <a class=\"mdl-navigation__link seleccionado\" href=\"#\" >Inicio</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"infoCliente\">Información adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content mdl-grid\">\n"
                    + "                    "+htmlExamenes+"\n"
                    + "                </div>\n"
                    + "            </main>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");

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

    public String obtenerExamenes() {
        Conexion base = new Conexion();
        examenes = new ArrayList<>();
        Examen examenx;

        String html = "";
        try {
            base.conectar();
            base.ejecuta("SET sql_mode=(SELECT REPLACE(@@sql_mode,\"ONLY_FULL_GROUP_BY\",\"\"));");
            ResultSet rs = base.ejecutaQuery("select * from Progre where idCliente = " + sesion.getAttribute("id") + ";");//"++"
            while (rs.next()) {
                examenx = new Examen(
                        rs.getInt("IdExamen"),
                        rs.getString("TituloExamen"),
                        (String)sesion.getAttribute("nombre"),
                        rs.getTimestamp("Fecha"),
                        rs.getInt("Progreso"),
                        rs.getInt("Calificacion"));
                examenes.add(examenx);
                System.out.println("si");
                switch (examenx.getEstado()) {
                    case Examen.NO_EMPEZADO:
                        html += "<div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                                + "                        <h2 class=\"mdl-card__title-text\">" + examenx.getTitulo() + "</h2><br>\n"
                                + "                        <p style=\"color: gray\">\n"
                                + "                            Fecha:" + examenx.getFecha() + "<br/>\n"
                                + "                        </p>\n"
                                + "                        <form method=\"POST\" action=\"aplicadorExamen\">\n"
                                + "                            <input type=\"hidden\" name=\"idExamen\" value=\""+examenx.getIdExamen()+"\" />\n"
                                + "                            <input type=\"submit\" value=\"Comenzar Examen\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\" />\n"
                                + "                        </form>"
                                + "                    </div>";
                        break;
                    case Examen.EN_PROCESO:
                        html += "<div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                                + "                        <h2 class=\"mdl-card__title-text\">" + examenx.getTitulo() + "</h2><br>\n"
                                + "                        <p style=\"color: gray\">\n"
                                + "                            Fecha:" + examenx.getFecha() + "<br/>\n"
                                + "                        </p>\n"
                                + "                        <p style=\"color: gray\">\n"
                                + "                            Ultima pregunta contestada:" + examenx.getUltimaPregunta() + "\n"
                                + "                        </p>\n"
                                + "                        <form method=\"POST\" action=\"aplicadorExamen\">\n"
                                + "                            <input type=\"hidden\" name=\"idExamen\" value=\""+examenx.getIdExamen()+"\" />\n"
                                + "                            <input type=\"submit\" value=\"Reanudar Examen\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\" />\n"
                                + "                        </form>"
                                + "                    </div>";
                        break;
                    case Examen.CONCLUIDO:
                        html += "<div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                                + "                        <h2 class=\"mdl-card__title-text\">" + examenx.getTitulo() + "</h2><br>\n"
                                + "                        <p style=\"color: gray\">\n"
                                + "                            Fecha:" + examenx.getFecha() + "<br/>\n"
                                + "                        </p>\n"
                                + "                        <p style=\"color: gray\">\n"
                                + "                            Calificacion:" + examenx.getCalificacion() + "\n"
                                + "                        </p>\n"
                                + "                        <form method=\"POST\" action=\"resultados\">\n"
                                + "                            <input type=\"hidden\" name=\"idExamen\" value=\""+examenx.getIdExamen()+"\" />\n"
                                + "                            <input type=\"submit\" value=\"Ver Resultados\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\" />\n"
                                + "                        </form>"
                                + "                    </div>";
                        break;
                }

            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return html;
    }

}
