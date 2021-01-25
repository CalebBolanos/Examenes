/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
public class procesarInicioSesion extends HttpServlet {

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
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        System.out.println(usuario);
        System.out.println(contrasena);
        boolean verificado = false;

        if (Validaciones.StringsNoVacios(usuario, contrasena)) {
            try {
                Conexion base = new Conexion();
                base.conectar();
                ResultSet rs = base.ejecutaQuery("call spIniciarSesion(\""+usuario+"\", \""+contrasena+"\");");
                if (rs.next()) {
                    if (rs.getString("msj").equals("ok")) {
                        verificado = true;
                        int id = rs.getInt("id");
                        int tipo = rs.getInt("tipo");
                        String nombre = rs.getString("nom");
                        String paterno = rs.getString("pat");
                        String materno = rs.getString("mat");
                        String correo = rs.getString("corr");

                        HttpSession sesion = request.getSession();
                        sesion.setAttribute("id", id);
                        sesion.setAttribute("tipo", tipo);
                        sesion.setAttribute("nombre", nombre);
                        sesion.setAttribute("paterno", paterno);
                        sesion.setAttribute("materno", materno);
                        sesion.setAttribute("correo", correo);
                    } 
                    base.cierraConexion();
                }
            } catch (SQLException ex) {
                Logger.getLogger(procesarInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (verificado) {
                response.sendRedirect("inicioCliente");
            } else {
                response.sendRedirect("iniciarSesion");
            }

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
