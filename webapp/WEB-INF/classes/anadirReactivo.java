/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author calebbolanos
 */
public class anadirReactivo extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html><html>\n"
                    + "    <head>\n"
                    + "        <title>Registrar Usuarios</title>\n"
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
                    + "                margin: 50px 50px 50px 50px;\n"
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
                    + "                    <span class=\"mdl-layout-title\">Anadir Reactivo</span>\n"
                    + "                    <div class=\"mdl-layout-spacer\"></div>\n"
                    + "                    <nav class=\"mdl-navigation mdl-layout--large-screen-only\">\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"infoAdmin\">Nombre</a>\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"cerrarSesion\">Cerrar Sesion</a>\n"
                    + "                    </nav>\n"
                    + "                </div>\n"
                    + "            </header>\n"
                    + "            <div class=\"mdl-layout__drawer\">\n"
                    + "                <span class=\"mdl-layout-title\">Examenes</span>\n"
                    + "                <nav class=\"mdl-navigation\">\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"inicioAdmin\" >Incio</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"registroUsuarios\">Registrar Usuarios</a>\n"
                    + "                    <a class=\"mdl-navigation__link seleccionado\" href=\"anadirReactivo\">Anadir Reactivos</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"crearExamen\">Crear Nuevo Examen</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"infoAdmin\">Informacion adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content mdl-grid\">\n"
                    + "                    <div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                    + "                        <h2 class=\"mdl-card__title-text\">Anadir Reactivos</h2>\n"
                    + "                        <p class=\"mdl-card__supporting-text\">Completa el siguiente formulario para anadir un reactivo a la base de datos:</p>\n"
                    + "                        <form method=\"POST\" action=\"anRe\">\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <textarea class=\"mdl-textfield__input\" type=\"text\" rows= \"3\" id=\"pregunta\" name=\"pregunta\" required></textarea>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"pregunta\">Pregunta</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"text\" id=\"opcionA\" name=\"opcionA\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"opcionA\">Opcion A</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"text\" id=\"opcionB\" name=\"opcionB\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"opcionB\">Opcion B</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"text\" id=\"opcionC\" name=\"opcionC\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"opcionC\">Opcion C</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"text\" id=\"opcionD\" name=\"opcionD\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"opcionD\">Opcion D</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"text\" id=\"respuesta\" name=\"respuesta\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"respuesta\">Respuesta</label>\n"
                    + "                            </div>\n"
                    + "                            <input type=\"submit\" value=\"Agregar reactivo\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--accent\" style=\"width: 100%\" />\n"
                    + "                        </form>\n"
                    + "                    </div>\n"
                    + "\n"
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

}
