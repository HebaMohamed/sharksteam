package com.mycompany.mavenserrr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

        request.getRequestDispatcher("newmonitor.jsp").forward(request, response);
        
        
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String hiddenflag = request.getParameter("hiddenflag");
        if(hiddenflag.equals("signup")){
                
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String gender = request.getParameter("Gender");
            String mpassword = request.getParameter("mpassword");
            String mcpassword = request.getParameter("mcpassword");
            
            String name= fname+" "+lname;
            if(mpassword.equals(mcpassword)){
                
                 try{
                     JSONObject tosend = new JSONObject();
                     tosend.put("name", name);
                     tosend.put("gender", gender);
                     tosend.put("password", mpassword);
               JSONObject resObj = DataClass.getJSONObject(URLsClass.addmonitormember, tosend.toString());
                int successf = resObj.getInt("success");

               if(successf==1){
                   String insertedid = String.valueOf(resObj.getInt("insertedid"));
                   DataClass.displayAlert(response,request,"Successfull Sign Up, Your account now in the pending state, please wait until acceptance, your ID is : "+insertedid,"/LoginServlet");
//                   request.getRequestDispatcher("index.jsp").forward(request, response);
                   //response.sendRedirect(request.getContextPath() + "/LoginServlet");
               }
                else
                {           
                    DataClass.displayAlert(response,request,resObj.getString("msg"),"/SignUpServlet");
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }

        }
        //processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
