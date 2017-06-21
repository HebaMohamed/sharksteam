/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenserrr;

import com.firebase.client.Firebase;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myclassespackage.DataClass;
import myclassespackage.EventWarning;
import myclassespackage.URLsClass;
import myclassespackage.Vehicle;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(name = "EventServlet", urlPatterns = {"/EventServlet"})
public class EventServlet extends HttpServlet {

        
    ArrayList<EventWarning> warnings = new ArrayList<EventWarning>();
    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
                request.getRequestDispatcher("eventspage.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                DataClass.checkSession(request, response);
                //processRequest(request, response);
                gohome(request, response);
                
                
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                gohome(request, response);
            }
            
    }

    void gohome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {   
            JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getwanings, "");
                    getWarningssData(obj);
                    request.setAttribute("warnings", warnings);  
                    request.setAttribute("vehicles", vehicles);  

                    emptyNotifications();
            
                    request.getRequestDispatcher("eventspage.jsp").forward(request, response);//show only
            } catch (NullPointerException ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + "/LoginServlet");

            } catch (Exception ex) {
                Logger.getLogger(EventServlet.class.getName()).log(Level.SEVERE, null, ex);

            } 
    }
    
    void emptyNotifications(){
        //empty notifications
        Firebase myFirebaseRef = new Firebase("https://sharksmapandroid-158200.firebaseio.com/");
        myFirebaseRef.child("notifications").child("driverwarning").setValue("");
    }
    
    void getWarningssData(JSONObject obj){
        warnings.clear();
        for (int i = 0; i < obj.getJSONArray("warning").size(); i++) {
            EventWarning w = new EventWarning();
            w.timestamp=obj.getJSONArray("warning").getJSONObject(i).getLong("timestamp");
            w.did=obj.getJSONArray("warning").getJSONObject(i).getInt("did");
            w.dname=obj.getJSONArray("warning").getJSONObject(i).getString("dname");
            w.datetxt = getdatetxt(w.timestamp);

            warnings.add(w);
        }
        
         vehicles.clear();
        for (int i = 0; i < obj.getJSONArray("poweredvehicles").size(); i++) {
            Vehicle v = new Vehicle(obj.getJSONArray("poweredvehicles").getJSONObject(i).getInt("vid"));
            v.Plate_number = "IOJOI88";//testttt//obj.getJSONArray("poweredvehicles").getJSONObject(i).getString("plate_number");

            vehicles.add(v);
        }
    }
    
     public String getdatetxt(Long ts){            
        Date d = new Date(ts);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(d);
        return s;
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
