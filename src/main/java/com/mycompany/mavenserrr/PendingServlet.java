package com.mycompany.mavenserrr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import myclassespackage.MonitoringMember;
import myclassespackage.URLsClass;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/PendingServlet"})
public class PendingServlet extends HttpServlet {

    ArrayList<MonitoringMember> members = new ArrayList<MonitoringMember>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            //ex 
            //DataClass.currentMM=new MonitoringMember(1, "ki", "omjio", "in");
            gohome(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(PendingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

        
    void gohome(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getpmembers, "");
                    getPendingData(obj);
                    request.setAttribute("members", members);  
                    request.getRequestDispatcher("pendingpage.jsp").forward(request, response);//show only
    }
    void getPendingData(JSONObject obj){
        members.clear();
        for (int i = 0; i < obj.getJSONArray("members").size(); i++) {
            
            MonitoringMember m = new MonitoringMember(
                    obj.getJSONArray("members").getJSONObject(i).getInt("user_id"), 
                    obj.getJSONArray("members").getJSONObject(i).getString("fullname"), 
                    "member", 
                    obj.getJSONArray("members").getJSONObject(i).getString("gender"));
            
            m.lastlogin_time=obj.getJSONArray("members").getJSONObject(i).getString("lastlogin_time");
        
            members.add(m);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try{
            DataClass.checkSession(request, response);
            
        String goflag= request.getParameter("goflag");

        if(goflag.equals("showpending")){
            response.sendRedirect(request.getContextPath() + "/PendingServlet"); 
        }
        else if(goflag.equals("approve")){
           // response.sendRedirect(request.getContextPath() + "/IndexServlet"); 
        }
        
        
        } catch (Exception ex) {
            Logger.getLogger(PendingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
