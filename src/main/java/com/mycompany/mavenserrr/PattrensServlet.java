/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenserrr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myclassespackage.DataClass;
import myclassespackage.Pattren;
import myclassespackage.Trip;
import myclassespackage.URLsClass;
import myclassespackage.Vehicle;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(name = "PattrensServlet", urlPatterns = {"/PattrensServlet"})
public class PattrensServlet extends HttpServlet {

    static ArrayList<Pattren> allpattrens = new ArrayList<Pattren>();
    
    String currentId;

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
                request.getRequestDispatcher("pattrenspage.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }
    
        void gohome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {   
            JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getpattrens, "");
                    getPattrenssData(obj);
                    request.setAttribute("pattrens", allpattrens);  
            
                    request.getRequestDispatcher("pattrenspage.jsp").forward(request, response);//show only
            } catch (NullPointerException ex) {
                Logger.getLogger(PattrensServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + "/LoginServlet");

            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);

            } 
    }
        void getPattrenssData(JSONObject obj){
        allpattrens.clear();
        for (int i = 0; i < obj.getJSONArray("pattrens").size(); i++) {
            Pattren p = new Pattren();
            p.id=obj.getJSONArray("pattrens").getJSONObject(i).getInt("id");
            p.max=obj.getJSONArray("pattrens").getJSONObject(i).getInt("max");
            p.name=obj.getJSONArray("pattrens").getJSONObject(i).getString("name");

            allpattrens.add(p);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                DataClass.checkSession(request, response);
                //processRequest(request, response);
//                gohome(request, response);
                
                String goflag= request.getParameter("goflag");
                if(goflag.equals("edit")){
                    String pid= request.getParameter("pid");
                    String pname= request.getParameter("pname");
                    int pmax= Integer.parseInt(request.getParameter("pmax"));

                    currentId=pid;
                    
                    
                Pattren p = new Pattren();
                p.id = Integer.parseInt(pid);
                p.name = pname;
                p.max = pmax;
                
                request.setAttribute("selectedpattren", p);
                request.getRequestDispatcher("editpattrenpage.jsp").forward(request, response);
//                    response.sendRedirect(request.getContextPath() + "/PattrensServlet?goflag=edit&pid="+pid);
                }
                
                
//                request.getRequestDispatcher("pattrenspage.jsp").forward(request, response);//show only
                
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                gohome(request, response);
            }
            
    }
    
    
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String hiddenflag = request.getParameter("hiddenflag");
        if(hiddenflag.equals("edit")){
            try{
            String pname = request.getParameter("pname");
            String pmax = request.getParameter("pmax");
            
            JSONObject o = new JSONObject();
            o.put("id", currentId);
            o.put("name", pname);
            o.put("max", pmax);

            JSONObject resObj = DataClass.getJSONObject(URLsClass.editpattren, o.toString());

            int successf = resObj.getInt("success");     
            if(successf==1){
                gohome(request,response);
            }
            else
            {           
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet NewServlet</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println(resObj.getString("msg"));
                    out.println("</body>");
                    out.println("</html>");   
                
            }
                

            } catch (Exception ex) {
                Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        processRequest(request, response);
    }
    
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
