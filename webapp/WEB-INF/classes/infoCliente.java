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
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class infoCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("id") == null || sesion.getAttribute("tipo") == null) {
            response.sendRedirect("iniciarSesion?respuesta=Sesion expirada. Vuelve a iniciar sesion");
            return;
        }
        if ((int) sesion.getAttribute("tipo") == 2) {
            response.sendRedirect("inicioAdmin");
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n"
                    + "<html>\n"
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
                    + "                    <span class=\"mdl-layout-title\">Información adicional</span>\n"
                    + "                    <div class=\"mdl-layout-spacer\"></div>\n"
                    + "                    <nav class=\"mdl-navigation mdl-layout--large-screen-only\">\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"#\">" + sesion.getAttribute("nombre") + "</a>\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"cerrarSesion\">Cerrar Sesion</a>\n"
                    + "                    </nav>\n"
                    + "                </div>\n"
                    + "            </header>\n"
                    + "            <div class=\"mdl-layout__drawer\">\n"
                    + "                <span class=\"mdl-layout-title\">Examenes</span>\n"
                    + "                <nav class=\"mdl-navigation\">\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"inicioCliente\" >Incio</a>\n"
                    + "                    <a class=\"mdl-navigation__link seleccionado\" href=\"#\">Información adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content mdl-grid\">\n"
                    + "                    <div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                    + "                        <h2 class=\"mdl-card__title-text\">Datos del cliente</h2><br>\n"
                    + "                        <p style=\"color: gray\">Nombre:" + sesion.getAttribute("nombre") + " " + sesion.getAttribute("paterno") + " " + sesion.getAttribute("materno") + "</p>\n"
                    + "                        <p style=\"color: gray\">Correo electronico: " + sesion.getAttribute("correo") + "</p>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                    + "                        <h2 class=\"mdl-card__title-text\">Acerca de</h2><br>\n"
                    + "                        <p style=\"color: gray\">Aplicador y evaluador de examenes de opción multiple <br>Proyecto de Programación Orientada a Objetos</p>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </main>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "");
        }
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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
