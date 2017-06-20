/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenserrr;

import com.firebase.client.Firebase;
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
import myclassespackage.Driver;
import myclassespackage.Passenger;
import myclassespackage.URLsClass;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(name = "PassengersServlet", urlPatterns = {"/PassengersServlet"})
public class PassengersServlet extends HttpServlet {
    
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        ArrayList<Passenger> filteredpassengers = new ArrayList<Passenger>();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //just show drivers
            JSONObject obj;
                obj = DataClass.getJSONObject(URLsClass.getpassengers, "");
                getPassengersData(obj);
              //  request.setAttribute("drivers", obj.getJSONArray("drivers"));
                request.setAttribute("passengers", passengers);  
              request.getRequestDispatcher("passengersjsp.jsp").forward(request, response);//show only
        }catch(Exception e){
            Logger.getLogger(PassengersServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            out.close();
        }
    }
        void getPassengersData(JSONObject obj){
        passengers.clear();
        for (int i = 0; i < obj.getJSONArray("passengers").size(); i++) {
            
            Passenger p = new Passenger(obj.getJSONArray("passengers").getJSONObject(i).getInt("pid"), 
                    "", 
                    obj.getJSONArray("passengers").getJSONObject(i).getString("name"), 
                    obj.getJSONArray("passengers").getJSONObject(i).getString("gender"), 
                    obj.getJSONArray("passengers").getJSONObject(i).getInt("age"), 
                    obj.getJSONArray("passengers").getJSONObject(i).getString("phone"), 
                    "", 
                    obj.getJSONArray("passengers").getJSONObject(i).getString("language"),
                    obj.getJSONArray("passengers").getJSONObject(i).getString("email"),
                    obj.getJSONArray("passengers").getJSONObject(i).getBoolean("active")
                    );
            passengers.add(p);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            DataClass.checkSession(request, response);
            if(DataClass.currentMM==null)
                response.sendRedirect(request.getContextPath() + "/LoginServlet");
              
            String goflag= request.getParameter("goflag");
            if(goflag.equals("activate")){
                String id= request.getParameter("id");
                Firebase  myFirebaseRef = new Firebase("https://sharksmapandroid-158200.firebaseio.com/");
                myFirebaseRef.child("passenger").child(id).child("active").setValue(true);
               response.sendRedirect(request.getContextPath() + "/PassengersServlet");
            }
            else if(goflag.equals("deactivate")){
                String id= request.getParameter("id");
                Firebase  myFirebaseRef = new Firebase("https://sharksmapandroid-158200.firebaseio.com/");
                myFirebaseRef.child("passenger").child(id).child("active").setValue(false);
                response.sendRedirect(request.getContextPath() + "/PassengersServlet");
            }
            
        }
        catch(Exception e){
            Logger.getLogger(PassengersServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        processRequest(request, response);

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String hiddenflag = request.getParameter("hiddenflag");
        if(hiddenflag.equals("search")){
            String srch = request.getParameter("searchtxt");
            searchPassengerr(srch);

            request.setAttribute("passengers", filteredpassengers);  
            request.getRequestDispatcher("passengersjsp.jsp").forward(request, response);//show only
        }
        else
            processRequest(request, response);
    }

        void searchPassengerr(String t){
        filteredpassengers.clear();
        for (int i = 0; i < passengers.size(); i++) {
            String name = passengers.get(i).FullName.toLowerCase();
            if(t.equals("")){
                filteredpassengers.add(passengers.get(i));
            }
            else if(name.equals(t.toLowerCase()) || String.valueOf(passengers.get(i).ID).equals(t)){
                filteredpassengers.add(passengers.get(i));
            }
            else{
                String[] splitStr = name.split("\\s+");
                int f = 0; //to add only oce
                for (int j = 0; j < splitStr.length; j++) {
                    if(splitStr[j].toLowerCase().equals(t.toLowerCase())){
                        f=1;
                    }
                }
                if(f==1)
                    filteredpassengers.add(passengers.get(i));
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
