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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Servlet en donde se genera dinamicamente codigo html para que el alumno 
 * pueda responder los reactivos de un examen
 */
public class aplicadorExamen extends HttpServlet {

    HttpSession sesion;
    Examen examen;
    Vector<Reactivo> reactivos;

    Conexion base;
    int posicionPregunta;

    String htmlPreguntas = "";

    /**
     * En processRequest verificamos que para tanto los metodos doGet y doPost
     * exista una sesion y que los atributos no esten nulos. En caso de que 
     * esto suceda se redirige al inicio de sesion. 
     * Si un administrador con sesion iniciada intenta entrar se le redigira 
     * al inicio de su seccion
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        sesion = request.getSession();
        if (sesion.getAttribute("id") == null || sesion.getAttribute("tipo") == null) {
            response.sendRedirect("iniciarSesion?respuesta=Sesion expirada. Vuelve a iniciar sesion");
            return;
        }
        if ((int) sesion.getAttribute("tipo") == 2) {
            response.sendRedirect("inicioAdmin");
            return;
        }

    }

    /**
     * Redirige al inicio
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.sendRedirect("inicioCliente");
    }

    /**
     * En do post se genera dinamicamente el codigo HTML utilizando otros metodos de la misma
     * clase y asi mostrar al usuario los reactivos del examen
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        base = new Conexion();
        int idExamen = Integer.parseInt(request.getParameter("idExamen"));
        System.out.println(idExamen);
        examen = obtenerExamen(idExamen);
        posicionPregunta = examen.getUltimaPregunta() + 1;
        System.out.println("ps cliente " + posicionPregunta);

        switch (examen.getEstado()) {
            case Examen.NO_EMPEZADO:
            case Examen.EN_PROCESO:
                obtenerReactivos();
                break;
            case Examen.CONCLUIDO:
                response.sendRedirect("inicioCliente");
                break;
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html>\n"
                    + "    <head>\n"
                    + "        <title>Registrar Usuarios</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://code.getmdl.io/1.3.0/material.blue-indigo.min.css\" />\n"
                    + "        <script defer src=\"https://code.getmdl.io/1.3.0/material.min.js\"></script>\n"
                    + "        <script type=\"text/javascript\">\n"
                    + "        function sendData(form) {\n"
                    + "                const XHR = new XMLHttpRequest();\n"
                    + "                const FD = new FormData( form );\n"
                    + "                XHR.addEventListener( \"load\", function(event) {\n"
                    + "                  alert( event.target.responseText );\n"
                    + "                } );\n"
                    + "                XHR.addEventListener( \"error\", function( event ) {\n"
                    + "                  //alert( 'Oops! Something went wrong.' );\n"
                    + "                } );\n"
                    + "                XHR.open( \"POST\", \"http://localhost:5050/procesarExamen\" );\n"
                    + "\n"
                    + "                XHR.send( FD );\n"
                    + "            }\n"
                    + "function submitForm(form) {\n"
                    + "                console.log(form);\n"
                    + "                sendData(form);\n"
                    + "                return false;\n"
                    + "            }"
                    + "            function cargarTodo(){\n"
                    + "             document.getElementById(\"reactivo" + posicionPregunta + "\").style.display = \"block\";\n"
                    + "            }\n"
                    + "            window.onload = cargarTodo;"
                    + "        </script>"
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
                    + "                    <span class=\"mdl-layout-title\">" + examen.getTitulo() + "</span>\n"
                    + "                    <div class=\"mdl-layout-spacer\"></div>\n"
                    + "                    <nav class=\"mdl-navigation mdl-layout--large-screen-only\">\n"
                    + "                    </nav>\n"
                    + "                </div>\n"
                    + "            </header>\n"
                    + "            <div class=\"mdl-layout__drawer\">\n"
                    + "                <span class=\"mdl-layout-title\">Examen</span>\n"
                    + "                <nav class=\"mdl-navigation\">\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"inicioCliente\" >Inicio</a>\n"
                    + "                    <a class=\"mdl-navigation__link disponible\" href=\"infoCliente\">Información adicional</a>\n"
                    + "                </nav>\n"
                    + "            </div>\n"
                    + "            <main class=\"mdl-layout__content\">\n"
                    + "                <div class=\"page-content mdl-grid\">\n"
                    + "                    " + htmlPreguntas + "\n"
                    + "                </div>\n"
                    + "            </main>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");
        }

    }
    
    
    /**
     * Este metodo obtiene la informacion de un examen dado su id y retorna 
     * un objeto de tipo Examen el cual contiene la informacion obtenida de 
     * la base de datos
     */
    public Examen obtenerExamen(int idExamen) {
        Examen examenx = null;
        try {
            base.conectar();
            base.ejecuta("SET sql_mode=(SELECT REPLACE(@@sql_mode,\"ONLY_FULL_GROUP_BY\",\"\"));");
            ResultSet rs = base.ejecutaQuery("select * from progre where idExamen = " + idExamen + " and idCliente = " + sesion.getAttribute("id") + ";");
            if (rs.next()) {
                examenx = new Examen(
                        rs.getInt("IdExamen"),
                        rs.getString("TituloExamen"),
                        (String) sesion.getAttribute("nombre"),
                        rs.getTimestamp("Fecha"),
                        rs.getInt("Progreso"),
                        rs.getInt("Calificacion"));
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return examenx;
    }
    
    /**
     * metodo encargado de obtener informacion de los reactivos del examen a 
     * aplicar. Cabe recalcar que aqui se acomoda dinamicamente la seccion de 
     * html que contiene los reactivos con apoyo del metodo generarHTMLReactivo()
     */
    public void obtenerReactivos() {
        int index = 0;
        reactivos = new Vector<>();
        Reactivo reactivox;
        try {
            base.conectar();
            ResultSet rs = base.ejecutaQuery("select * from mostraexa where idExamen = " + examen.getIdExamen() + ";");//"++"
            while (rs.next()) {
                index++;
                reactivox = new Reactivo(
                        rs.getInt("idPregunta"),
                        rs.getString("pregunta"),
                        rs.getString("opcionA"),
                        rs.getString("opcionB"),
                        rs.getString("opcionC"),
                        rs.getString("opcionD"),
                        Reactivo.SIN_RESPONDER
                );
                reactivos.add(reactivox);
                htmlPreguntas += generarHTMLReactivo(reactivox, index);
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * metodo encargado de generar dinamicamente codigo HTML el cual acomoda 
     * la informacion de un reactivo del examen dentro un form, el cual una 
     * vez contestado se hace el request al servlet y se guarda en la base de datos
     */
    public String generarHTMLReactivo(Reactivo reactivo, int i) {
        String html = "<div class=\"tarjeta mdl-cell mdl-cell--12-col\" id=\"reactivo" + i + "\" style=\"display: none;\" >\n"//block, none
                + "                        <form id=\"form" + i + "\" method=\"POST\" action=\"procesarExamen\" >\n"//target=\"_blank\"
                + "                            <p style=\"color: gray; font-size: 17px;\">\n"
                + "                                " + reactivo.getPregunta() + "\n"
                + "                            </p>\n"
                + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcion" + i + "A\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                + "                                <input type=\"radio\" id=\"opcion" + i + "A\" class=\"mdl-radio__button\" name=\"opciones\" value=\"" + reactivo.getOpcionA() + "\" >\n"
                + "                                <span class=\"mdl-radio__label\">" + reactivo.getOpcionA() + "</span>\n"
                + "                            </label>\n"
                + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcion" + i + "B\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                + "                                <input type=\"radio\" id=\"opcion" + i + "B\" class=\"mdl-radio__button\" name=\"opciones\" value=\"" + reactivo.getOpcionB() + "\" >\n"
                + "                                <span class=\"mdl-radio__label\">" + reactivo.getOpcionB() + "</span>\n"
                + "                            </label>\n"
                + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcion" + i + "C\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                + "                                <input type=\"radio\" id=\"opcion" + i + "C\" class=\"mdl-radio__button\" name=\"opciones\" value=\"" + reactivo.getOpcionC() + "\" >\n"
                + "                                <span class=\"mdl-radio__label\">" + reactivo.getOpcionC() + "</span>\n"
                + "                            </label>\n"
                + "                            <label class=\"mdl-radio mdl-js-radio mdl-js-ripple-effect\" for=\"opcion" + i + "D\" style=\"width: 100%; margin-bottom: 5px;\">\n"
                + "                                <input type=\"radio\" id=\"opcion" + i + "D\" class=\"mdl-radio__button\" name=\"opciones\" value=\"" + reactivo.getOpcionD() + "\" >\n"
                + "                                <span class=\"mdl-radio__label\">" + reactivo.getOpcionD() + "</span>\n"
                + "                            </label>\n"
                + "                            <input type=\"hidden\" name=\"idReactivo\" value=\"" + reactivo.getIdReactivo() + "\" />\n"
                + "                            <input type=\"hidden\" name=\"idExamen\" value=\"" + examen.getIdExamen() + "\" />\n"
                + "                            <input type=\"hidden\" name=\"posicionPregunta\" value=\"" + i + "\" />\n"
                + "                            <input type=\"hidden\" name=\"tipoTransaccion\" value=\"1\" />\n"
                + "                            <input type=\"submit\" value=\"Siguiente\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\">\n"
                + "                        </form></div>";
        return html;
    }

    @Override
    public String getServletInfo() {
        return "Short description";

    }

}
