package com.mycompany.mavenserrr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import myclassespackage.Trip;
import myclassespackage.URLsClass;
import myclassespackage.Vehicle;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/TripServlet"})
public class TripServlet extends HttpServlet {

        static ArrayList<Trip> alltrips = new ArrayList<Trip>();
        ArrayList<Trip> filterdtrips = new ArrayList<Trip>();
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

        request.getRequestDispatcher("trips.jsp").forward(request, response);
        
        
        } finally {
            out.close();
        }
    }
    
        
    void gohome(HttpServletRequest request, HttpServletResponse response) {
        try {   
            JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.gettrips, "");
                    getTripsData(obj);
                    request.setAttribute("trips", alltrips);  
            
                request.getRequestDispatcher("trips.jsp").forward(request, response);//show only
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
        
    void getTripsData(JSONObject obj){
        alltrips.clear();
        for (int i = 0; i < obj.getJSONArray("trips").size(); i++) {
            Trip t = new Trip(
                    obj.getJSONArray("trips").getJSONObject(i).getInt("trip_id"), 
                    obj.getJSONArray("trips").getJSONObject(i).getString("start"), 
                    obj.getJSONArray("trips").getJSONObject(i).getString("end"), 
                    obj.getJSONArray("trips").getJSONObject(i).getDouble("price"), 
                    obj.getJSONArray("trips").getJSONObject(i).getString("comment"), 
                    obj.getJSONArray("trips").getJSONObject(i).getInt("ratting"));
            t.p= new Passenger(obj.getJSONArray("trips").getJSONObject(i).getInt("passenger_id"));
            t.d= new Driver(obj.getJSONArray("trips").getJSONObject(i).getInt("driver_id"));
            t.staticmapurl=getpathwaymap(obj.getJSONArray("trips").getJSONObject(i).getInt("trip_id"));
            alltrips.add(t);
        }
    }
    
    String getpathwaymap(int tripid){
        String staticmapurl = "https://maps.googleapis.com/maps/api/staticmap?";
            try {
                JSONObject obj = DataClass.getJSONObject(URLsClass.getpathwaymap+"/"+tripid, "");
                
                int centersize = obj.getJSONArray("pathwaymap").size()/2;
                String latcenter = obj.getJSONArray("pathwaymap").getJSONObject(centersize).getString("yattitude");
                String lngcenter = obj.getJSONArray("pathwaymap").getJSONObject(centersize).getString("xlongitude");
                
                staticmapurl += "center="+latcenter+","+lngcenter+"&size=290x110&path=color:0x0000ff|weight:5";//&zoom=14

                for (int i = 0; i < obj.getJSONArray("pathwaymap").size(); i++) {
                    String lat = obj.getJSONArray("pathwaymap").getJSONObject(i).getString("yattitude");
                    String lng = obj.getJSONArray("pathwaymap").getJSONObject(i).getString("xlongitude");
                    staticmapurl+="|"+lat+","+lng;
                }
                staticmapurl+="&key="+URLsClass.apikey;
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            return staticmapurl;
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                DataClass.checkSession(request, response);
                //processRequest(request, response);
//                gohome(request, response);
                
                String goflag= request.getParameter("goflag");
                if(goflag.equals("today")){
                    filterTrips(new Date());
                    request.setAttribute("trips", filterdtrips);  
                }
                else if(goflag.equals("lastweek")){
                    filterTrips(getLastWeek());
                    request.setAttribute("trips", filterdtrips);  
                }
                else if(goflag.equals("lastmonth")){
                    filterTrips(getLastMonth());
                    request.setAttribute("trips", filterdtrips);  
                }
                else if(goflag.equals("lastyear")){
                    filterTrips(getLastYear());
                    request.setAttribute("trips", filterdtrips);  
                }
                else if(goflag.equals("all")){
                    request.setAttribute("trips", alltrips);  
                }
                else{
                    gohome(request, response);
                }
                
                request.getRequestDispatcher("trips.jsp").forward(request, response);//show only
                
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                gohome(request, response);
            }
            
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
    
    void filterTrips(Date lastdate){
        filterdtrips.clear();
        Date today = new Date();
        for (int i = 0; i < alltrips.size(); i++) {
            Date d = alltrips.get(i).start_Date;
            if(d.after(lastdate) || DateUtils.isSameDay(d,today)){
                filterdtrips.add(alltrips.get(i));
            }
        }
    }
    void filterTodayTrips(){
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        filterdtrips.clear();
        for (int i = 0; i < alltrips.size(); i++) {
            Date d = alltrips.get(i).start_Date;
            if(DateUtils.isSameDay(d,today)){
                filterdtrips.add(alltrips.get(i));
            }
        }
    }
        
    public Date getLastWeek()
    {
        Calendar c = Calendar.getInstance();
        c = (Calendar) c.clone();
        c.add(Calendar.WEEK_OF_YEAR, -1);// last week
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        Date date = c.getTime();
        return date;
    }
        
    public Date getLastMonth()
    {
        Calendar c = Calendar.getInstance();
        c = (Calendar) c.clone();
        c.add(Calendar.MONTH, -1);// last month
        c.set(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek());
        Date date = c.getTime();
        return date;
    }
    
    public Date getLastYear()
    {
        Calendar c = Calendar.getInstance();
        c = (Calendar) c.clone();
        c.add(Calendar.YEAR, -1);// last month
        c.set(Calendar.DAY_OF_YEAR, c.getFirstDayOfWeek());
        Date date = c.getTime();
        return date;
    }

}
