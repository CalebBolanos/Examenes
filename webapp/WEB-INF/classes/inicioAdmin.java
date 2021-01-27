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

public class inicioAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("id") == null || sesion.getAttribute("tipo") == null) {
            response.sendRedirect("iniciarSesion?respuesta=Sesion expirada. Vuelve a iniciar sesion");
            return;
        }
        if ((int) sesion.getAttribute("tipo") == 1) {
            response.sendRedirect("inicioCliente");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html><html>\n"
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
                    + "            .botones{\n"
                    + "                margin-top: 10px; \n"
                    + "                width: 100%;\n"
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
                    + "                        <a class=\"mdl-navigation__link\" href=\"infoAdmin\">" + sesion.getAttribute("nombre") + "</a>\n"
                    + "                        <a class=\"mdl-navigation__link\" href=\"cerrarSesion\">Cerrar Sesion</a>\n"
                    + "                    </nav>\n"
                    + "                </div>\n"
                    + "            </header>\n"
                    + "            <div class=\"mdl-layout__drawer\">\n"
                    + "                <span class=\"mdl-layout-title\">Examenes</span>\n"
                    + "                <nav class=\"mdl-navigation\">\n"
                    + "                    <a class=\"mdl-navigation__link seleccionado\" href=\"#\" >Incio</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"registroUsuarios\">Registrar Usuarios</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"anadirReactivo\">Añadir Reactivos</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"crearExamen\">Crear Nuevo Examen</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"infoAdmin\">Información adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content layout\">\n"
                    + "                    <div>\n"
                    + "                        <h4>Menu principal</h4>\n"
                    + "                        <a href=\"registroUsuarios\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent botones\">\n"
                    + "                            Registrar Usuarios\n"
                    + "                        </a><br />\n"
                    + "                        <a href=\"anadirReactivo\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent botones\">\n"
                    + "                            Añadir Reactivos\n"
                    + "                        </a><br />\n"
                    + "                        <a href=\"crearExamen\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent botones\">\n"
                    + "                            Crear Nuevo Examen\n"
                    + "                        </a><br />\n"
                    + "                        <a href=\"infoAdmin\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent botones\">\n"
                    + "                            Información adicional\n"
                    + "                        </a>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </main>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");
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
