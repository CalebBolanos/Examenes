
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

/*import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;*/


public class ProcesarRegistroCliente extends HttpServlet {
private Conexion base;
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
		

		
		String usuario=request.getParameter("nombre");
		String usuariop=request.getParameter("paterno");
		String usuariom=request.getParameter("materno");
		String usuarioc=request.getParameter("correo");
		String usuariocont=request.getParameter("contrasena");
		String c="c";
		
		System.out.println(usuario);
		System.out.println(usuariop);
		System.out.println(usuariom);
		System.out.println(usuarioc);
		System.out.println(usuariocont);
		
		
		if(Validaciones.StringsNoVacios(usuario,usuariop,usuariom,usuarioc,usuariocont)){
               try {
				   Conexion base = new Conexion();
                   base.conectar();
				   ResultSet rs = base.ejecutaQuery("call spRegistrarCliente(\"" + usuario + "\", \"" + usuariop + "\", \"" + usuariom + "\", \"" + usuarioc + "\", \"" + usuariocont + "\",\""+c+"\");");
                   if (rs.next()) {
                        if (rs.getString("msj3").equals("Se agrego nuevo usuario")) {
                            response.sendRedirect("registroUsuarios");
                        } 
						base.cierraConexion();
                    }
               } catch (SQLException ex) {
				    System.out.println(ex.toString());
               }
           }
		   else{
			   System.out.println("Porfavor no dejes espacios en blanco.");
			   
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
