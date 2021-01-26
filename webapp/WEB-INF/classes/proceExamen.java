
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


public class proceExamen extends HttpServlet {
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
		
		int id;
		String fe = request.getParameter("fecha");
		String ti = request.getParameter("tiempo");
		String nom = request.getParameter("nombre");
		
		System.out.println(fe);
		System.out.println(ti);
		System.out.println(nom);
		
		if(Validaciones.StringsNoVacios(fe,ti,nom)){
               try {
				   Conexion base = new Conexion();
                   base.conectar();
				   ResultSet rs = base.ejecutaQuery("call CreaExam(\"" + fe + "\", \"" + ti + "\");");
				   if(rs.next()){
                    id=rs.getInt("idExamen");
                    ResultSet rs2=base.ejecutaQuery("call RandRe(\"" + id + "\", \"" + nom+ "\");");
                    ResultSet rs3=base.ejecutaQuery("call ExaClie("+id+");");
					if(rs2.next()){
                        if (rs2.getString("msj").equals("ok")) {
                            //JOptionPane.showMessageDialog(null, "Examen Agregado Corrrectamente");
							response.sendRedirect("inicioAdmin");
                        } else {
                            //JOptionPane.showMessageDialog(null, rs.getString("msj1"));
							System.out.println(rs.getString("msj1"));
                        }   
                   }
                  } 
			  base.cierraConexion();
               }
			   
			 catch (SQLException ex) {
                   //Logger.getLogger(CreadorExamen.class.getName()).log(Level.SEVERE, null, ex);
				   System.out.println(ex.toString());
               }
           }else {
                //JOptionPane.showMessageDialog(null, "Llena todos los campos vacios", "Agregar Admin", JOptionPane.INFORMATION_MESSAGE);
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
