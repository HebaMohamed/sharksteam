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
import myclassespackage.Driver;
import myclassespackage.MonitoringMember;
import myclassespackage.URLsClass;
import myclassespackage.Vehicle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/MonitorServlet"})
public class MonitorServlet extends HttpServlet {

    
    ArrayList<Vehicle> activevehicles = new ArrayList<Vehicle>();
    ArrayList<Driver> activedrivers = new ArrayList<Driver>();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //for ex
//            DataClass.currentMM=new MonitoringMember(1, "ki", "omjio", "in");

            gohome(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MonitorServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    void gohome(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getcurrentvds, "");
                    getVDData(obj);
                    request.setAttribute("activevehicles", activevehicles); 
                    request.setAttribute("activedrivers", activedrivers);  
                    request.setAttribute("passengerscount", obj.getString("passengerscount"));  
                    request.setAttribute("tripscount", obj.getString("tripscount"));  
                    request.setAttribute("alldriverscount", obj.getString("alldriverscount"));  
                    request.setAttribute("onlineDriversCount", obj.getString("onlineDriversCount"));  
                    request.setAttribute("offlineDriversCount", obj.getString("offlineDriversCount"));  
                    
                    ArrayList<String> tripsids = getrattingtripsIDs(obj.getJSONArray("rattingArr"));
                    ArrayList<Integer> tripsratings = getRattingtrips(obj.getJSONArray("rattingArr"));
                    
                    request.setAttribute("tripsids", tripsids);  
                    request.setAttribute("tripsratings", tripsratings);  
                    
                    request.getRequestDispatcher("monitorpage.jsp").forward(request, response);//show only
    }
        
    void getVDData(JSONObject obj){
        activevehicles.clear();
        activedrivers.clear();
        //obj.getJSONArray("currentvms").getJSONObject(i).getInt("vehicle_id")
        for (int i = 0; i < obj.getJSONArray("currentvms").size(); i++) {
            Vehicle v = new Vehicle(obj.getJSONArray("currentvms").getJSONObject(i).getInt("vehicle_id"),
                                    obj.getJSONArray("currentvms").getJSONObject(i).getString("model"), 
                                    obj.getJSONArray("currentvms").getJSONObject(i).getString("color"),
                                    0, 0, "yes",
                                    obj.getJSONArray("currentvms").getJSONObject(i).getString("plate_number"));
            activevehicles.add(v);
            Driver d = new Driver(Integer.parseInt(obj.getJSONArray("currentvms").getJSONObject(i).getString("driver_id")),
                                                    obj.getJSONArray("currentvms").getJSONObject(i).getString("fullname"),
                                                    "", "", "");
            activedrivers.add(d);
        }
    }

    ArrayList<String> getrattingtripsIDs(JSONArray arr){
        ArrayList<String> ar = new ArrayList<String>();
        for (int i = 0; i < arr.size(); i++) {
            JSONObject o = arr.getJSONObject(i);
            String s = "TripID("+o.getInt("trip_id")+")";
            ar.add(s);
        }
        return ar;
    }
    ArrayList<Integer> getRattingtrips(JSONArray arr){
        ArrayList<Integer> ar = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) {
            JSONObject o = arr.getJSONObject(i);
            ar.add(o.getInt("ratting"));
        }
        return ar;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        DataClass.checkSession(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MonitorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
