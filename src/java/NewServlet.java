/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    Connection conn;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet attt " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
            //communicating a simple String message.
    String message = "Example source code of Servlet to JSP communication.";
    request.setAttribute("message", message);
   // request.getSession().setAttribute("ss", "a");
        request.setAttribute("ss", "as");
      ///////////////////////////  request.getSession().setAttribute("sss", "knkn" );
     //  getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
     //  request.getRequestDispatcher("index.jsp").forward(request,response);
     
    // RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
    //view.forward(request,response); 
    
        //response.sendRedirect("index.jsp");


        //request.getSession().setAttribute("sss", x );
                
                
                
                
                String xid = "000";
                String xname = "000";
                String xemail = "000";
                String xpassword = "000";

                
                        
                try {
            
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    //conn = DriverManager.getConnection("jdbc:mysql://www.db4free.net/hebadb?" + "user=heba&password=hebadbhebadb1");
                    conn = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com/sql8140605?" + "user=sql8140605&password=721NMcEB4F");

                    Statement st = conn.createStatement();
                    ResultSet rs=st.executeQuery("select * from users");
                    while(rs.next())
                     {
                         if(rs.getInt(1)==1){
                            xid = rs.getString(1);
                            xname = rs.getString(2);
                            xemail = rs.getString(3);
                            xpassword = rs.getString(4);
                         }

                     }; // 3shn mra 3rfa enha htb2a mra w7da bs :D
                     request.getSession().setAttribute("xid", xid );
                     request.getSession().setAttribute("xname", xname );
                     request.getSession().setAttribute("xemail", xemail );
                     request.getSession().setAttribute("xpassword", xpassword );

        
                } catch (Exception ex) {
                    Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                

        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    
        
        
    }
    public String connect() {
                    
        String x = "000";
        return"";
    }



        


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
