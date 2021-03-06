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

public class crearExamen extends HttpServlet {

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
            return;
        }
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
                    + "                    <span class=\"mdl-layout-title\">Crear Examen</span>\n"
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
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"inicioAdmin\" >Incio</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"registroUsuarios\">Registrar Usuarios</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"anadirReactivo\">Añadir Reactivos</a>\n"
                    + "                    <a class=\"mdl-navigation__link seleccionado\" href=\"crearExamen\">Crear Nuevo Examen</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"infoAdmin\">Información adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content mdl-grid\">\n"
                    + "                    <div class=\"tarjeta mdl-cell mdl-cell--12-col\">\n"
                    + "                        <h2 class=\"mdl-card__title-text\">Crear nuevo examen</h2>\n"
                    + "                        <p class=\"mdl-card__supporting-text\">Completa el siguiente formulario para asignar un nuevo examen:</p>\n"
                    + "                        <form method=\"POST\" action=\"proceExamen\">\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"date\" id=\"fecha\" name=\"fecha\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"fecha\">Fecha</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"time\" id=\"tiempo\" name=\"tiempo\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"tiempo\">Duracion</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\" style=\"width: 100%\">\n"
                    + "                                <input class=\"mdl-textfield__input\" type=\"text\" id=\"nombre\" name=\"nombre\" required>\n"
                    + "                                <label class=\"mdl-textfield__label\" for=\"nombre\">Nombre del examen</label>\n"
                    + "                            </div>\n"
                    + "                            <input type=\"submit\" value=\"Crear Examen\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--accent\" style=\"width: 100%\" />\n"
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
