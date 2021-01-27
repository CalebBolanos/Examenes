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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
