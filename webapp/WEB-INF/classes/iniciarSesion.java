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

public class iniciarSesion extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <title>Generador de examenes</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://code.getmdl.io/1.3.0/material.blue-indigo.min.css\" />\n"
                    + "        <script defer src=\"https://code.getmdl.io/1.3.0/material.min.js\"></script>\n"
                    + "        <style>\n"
                    + "            .tarjeta {\n"
                    + "                width: 320px;\n"
                    + "                height: auto;\n"
                    + "                padding: 20px;\n"
                    + "                margin-top: 50px;\n"
                    + "                box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);\n"
                    + "                transition: all 0.3s cubic-bezier(.25,.8,.25,1);\n"
                    + "            }\n"
                    + "\n"
                    + "            .tarjeta:hover {\n"
                    + "                box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);\n"
                    + "            }\n"
                    + "\n"
                    + "            .layout {\n"
                    + "                display: flex;\n"
                    + "                justify-content: center;\n"
                    + "                align-items: center;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"layout\">\n"
                    + "            <div class=\"tarjeta\">\n"
                    + "                <h2 class=\"mdl-card__title-text\">Iniciar Sesion</h2>\n"
                    + "                <form method=\"POST\" action=\"procesarInicioSesion\">\n"
                    + "                    <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n"
                    + "                        <input class=\"mdl-textfield__input\" type=\"text\" id=\"usuario\" name=\"usuario\" required>\n"
                    + "                        <label class=\"mdl-textfield__label\" for=\"usuario\">Usuario</label>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n"
                    + "                        <input class=\"mdl-textfield__input\" type=\"password\" id=\"contrasena\" name=\"contrasena\" required>\n"
                    + "                        <label class=\"mdl-textfield__label\" for=\"contrasena\">Contrasena</label>\n"
                    + "                    </div>\n"
                    + "                    <input type=\"submit\" value=\"Iniciar Sesion\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--accent\" />\n"
                    + "                </form>\n"
                    + "            </div>\n"
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
