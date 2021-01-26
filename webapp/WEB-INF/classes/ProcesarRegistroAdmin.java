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
 * @author lalo
 */
public class ProcesarRegistroAdmin extends HttpServlet {

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
        String usuario = request.getParameter("nombreAdmin");
        String usuariop = request.getParameter("paternoAdmin");
        String usuariom = request.getParameter("maternoAdmin");
        String usuarioc = request.getParameter("correoAdmin");
        String usuariocont = request.getParameter("contrasenaAdmin");
        String a="a";
    
        System.out.println(usuario);
        System.out.println(usuariop);
        System.out.println(usuariom);
        System.out.println(usuarioc);
        System.out.println(usuariocont);
        /*boolean verificado = false;*/

        if (Validaciones.StringsNoVacios(usuario, usuariop,usuariom,usuarioc,usuariocont)) {
            try {
                Conexion base = new Conexion();
                base.conectar();
                ResultSet rs = base.ejecutaQuery("call spRegistrarCliente(\"" + usuario + "\", \"" + usuariop + "\", \"" + usuariom + "\", \"" + usuarioc + "\", \"" + usuariocont + "\",\""+a+"\");");
                if (rs.next()) {
                    if (rs.getString("msj1").equals("Se agrego nuevo Admin")) {

                        response.sendRedirect("registroUsuarios");
                    } 
                    base.cierraConexion();
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            
        }
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
