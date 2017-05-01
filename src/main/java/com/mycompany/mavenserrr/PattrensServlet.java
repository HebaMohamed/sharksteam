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
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(name = "PattrensServlet", urlPatterns = {"/PattrensServlet"})
public class PattrensServlet extends HttpServlet {

    static ArrayList<Pattren> allpattrens = new ArrayList<Pattren>();

     
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
    
        void gohome(HttpServletRequest request, HttpServletResponse response) {
        try {   
            JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getpattrens, "");
                    getPattrenssData(obj);
                    request.setAttribute("pattrens", allpattrens);  
            
                    request.getRequestDispatcher("pattrenspage.jsp").forward(request, response);//show only
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
            try {
                DataClass.checkSession(request, response);
                //processRequest(request, response);
//                gohome(request, response);
                
                String goflag= request.getParameter("goflag");
                if(goflag.equals("edit")){
                    String pid= request.getParameter("pid");
                    response.sendRedirect(request.getContextPath() + "/PattrensServlet?goflag=edit&id="+pid);
                }
                
//                request.getRequestDispatcher("pattrenspage.jsp").forward(request, response);//show only
                
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                gohome(request, response);
            }
            
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
