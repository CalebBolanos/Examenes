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
public class aplicadorExamen extends HttpServlet {

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
                    + "                    <span class=\"mdl-layout-title\">Titulo del examen</span>\n"
                    + "                    <div class=\"mdl-layout-spacer\"></div>\n"
                    + "                    <nav class=\"mdl-navigation mdl-layout--large-screen-only\">\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"infoCliente\">Nombre</a>\n"
                    + "                        <a class=\"mdl-navigation__link\">Tiempo para pregunta: 00:00</a>\n"
                    + "                    </nav>\n"
                    + "                </div>\n"
                    + "            </header>\n"
                    + "            <div class=\"mdl-layout__drawer\">\n"
                    + "                <span class=\"mdl-layout-title\">Examen</span>\n"
                    + "                <nav class=\"mdl-navigation\">\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"\" >Inicio</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"\">Información adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content mdl-grid\">\n"
                    + "                    <div class=\"tarjeta mdl-cell mdl-cell--12-col\" id=\"reactivox\">\n"
                    + "                        <form method=\"POST\" action=\"#\">\n"
                    + "                            <p style=\"color: gray; font-size: 17px;\">\n"
                    + "                                Pregunta muy larga\n"
                    + "                            </p>\n"
                    + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcionxA\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                    + "                                <input type=\"radio\" id=\"opcionxA\" class=\"mdl-radio__button\" name=\"opciones\" value=\"Opcion 1\" >\n"
                    + "                                <span class=\"mdl-radio__label\">Opcion 1</span>\n"
                    + "                            </label>\n"
                    + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcionxB\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                    + "                                <input type=\"radio\" id=\"opcionxB\" class=\"mdl-radio__button\" name=\"opciones\" value=\"Opcion 2\" >\n"
                    + "                                <span class=\"mdl-radio__label\">Opcion 2</span>\n"
                    + "                            </label>\n"
                    + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcionxC\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                    + "                                <input type=\"radio\" id=\"opcionxC\" class=\"mdl-radio__button\" name=\"opciones\" value=\"Opcion 3\" >\n"
                    + "                                <span class=\"mdl-radio__label\">Opcion 3</span>\n"
                    + "                            </label>\n"
                    + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcionxD\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                    + "                                <input type=\"radio\" id=\"opcionxD\" class=\"mdl-radio__button\" name=\"opciones\" value=\"Opcion 4\" >\n"
                    + "                                <span class=\"mdl-radio__label\">Opcion 4</span>\n"
                    + "                            </label>\n"
                    + "                            <input type=\"submit\" value=\"Siguiente\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\">\n"
                    + "                        </form>\n"
                    + "                    </div>\n"
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
