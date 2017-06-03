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
import net.sf.json.JSONArray;
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
        
        int todaycount=0;
        int todayearnings=0;

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
    
        
    void gohome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {   
            JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.gettrips, "");
                    getTripsData(obj);
                    request.setAttribute("trips", alltrips);  
                    request.setAttribute("todaycount", String.valueOf(todaycount));
                    request.setAttribute("todayearnings", String.valueOf(todayearnings));

                request.getRequestDispatcher("trips.jsp").forward(request, response);//show only
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + "/LoginServlet");
            } 
    }
        
    void getTripsData(JSONObject obj) throws Exception{
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
            t.staticmapurl=getpathwaymap(obj.getJSONArray("trips").getJSONObject(i).getInt("trip_id"),290,110);
            
            t.from_addr=getAddress(obj.getJSONArray("trips").getJSONObject(i).getDouble("fromlat"),obj.getJSONArray("trips").getJSONObject(i).getDouble("fromlng"));
            t.to_addr=getAddress(obj.getJSONArray("trips").getJSONObject(i).getDouble("tolat"),obj.getJSONArray("trips").getJSONObject(i).getDouble("tolng"));

            t.lattitude = obj.getJSONArray("trips").getJSONObject(i).getDouble("fromlat");
            t.longtude = obj.getJSONArray("trips").getJSONObject(i).getDouble("fromlng");

            alltrips.add(t);
            
            //get today count
            filterTrips(new Date());
            
        }
    }
    
    String getAddress(double lat, double lng){
            //get addresses
            String formatted_address;
            JSONObject addr;
            try {
                addr = DataClass.getJSONObject("http://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=true", "");
                if(!addr.getJSONArray("results").isEmpty())
                    formatted_address = addr.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                else 
                    formatted_address = "Wrong Coordinates";
          } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                formatted_address = "Unmoved Trip";
            }
        return formatted_address;            
    }
    
    String getpathwaymap(int tripid, int width, int height){
        String staticmapurl = "https://maps.googleapis.com/maps/api/staticmap?";
            try {
                JSONObject obj = DataClass.getJSONObject(URLsClass.getpathwaymap+"/"+tripid, "");
                
                int centersize = obj.getJSONArray("pathwaymap").size()/2;
                String latcenter = obj.getJSONArray("pathwaymap").getJSONObject(centersize).getString("yattitude");
                String lngcenter = obj.getJSONArray("pathwaymap").getJSONObject(centersize).getString("xlongitude");
                
                staticmapurl += "center="+latcenter+","+lngcenter+"&size="+width+"x"+height+"&path=color:0x0000ff|weight:5";//&zoom=14

                for (int i = 0; i < obj.getJSONArray("pathwaymap").size(); i++) {
                    String lat = obj.getJSONArray("pathwaymap").getJSONObject(i).getString("yattitude");
                    String lng = obj.getJSONArray("pathwaymap").getJSONObject(i).getString("xlongitude");
                    staticmapurl+="|"+lat+","+lng;
                }
                staticmapurl+="&key="+URLsClass.apikey;
            } catch (Exception ex) {
//                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                staticmapurl="";
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
                else if(goflag.equals("showtrip")){
                    String tid = request.getParameter("tid");
                    JSONObject resObj = DataClass.getJSONObject(URLsClass.gettrip+tid+"/", "");
                    int successf = resObj.getInt("success");
                    if(successf==1){
                        
                        JSONObject t = resObj.getJSONObject("trip");
                        Trip tt = new Trip(Integer.parseInt(tid),
                                t.getString("start"),
                                t.getString("end"),
                                t.getDouble("price"), 
                                t.getString("comment"),
                                t.getInt("ratting"));
                        tt.d=new Driver(t.getInt("did"));
                        tt.d.name=t.getString("drivername");
                        tt.p=new Passenger(t.getInt("pid"));
                        tt.p.FullName=t.getString("passengername");
                        tt.status=t.getString("status");
                        
                        tt.lattitude = t.getDouble("ilat");
                        tt.longtude = t.getDouble("ilng");
                        
                        tt.from_addr=getAddress(t.getDouble("ilat"), t.getDouble("ilng"));
                        tt.to_addr=getAddress(t.getDouble("destlat"), t.getDouble("destlng"));
                        
                        tt.staticmapurl=getpathwaymap(Integer.parseInt(tid),800,300);

                        
                        request.setAttribute("selectedtrip", tt);
                        
                        JSONArray pattrens = resObj.getJSONArray("pattrens");
                        int p1=0,p2=0,p3=0,p4=0,p5=0,p6=0,p7=0,p8=0,p9=0,p10=0,p11=0,p12=0;
                        
                        for (int i = 0; i < pattrens.size(); i++) {
                            if(pattrens.getJSONObject(i).getInt("pattrenid")==1)
                                p1++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==2)
                                p2++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==3)
                                p3++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==4)
                                p4++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==5)
                                p5++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==6)
                                p6++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==7)
                                p7++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==8)
                                p8++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==9)
                                p9++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==10)
                                p10++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==11)
                                p11++;
                            else if(pattrens.getJSONObject(i).getInt("pattrenid")==12)
                                p12++;
                            
                            
                        }
                        
                        request.setAttribute("p1_name", resObj.getString("p1_name"));
                        request.setAttribute("p2_name", resObj.getString("p2_name"));
                        request.setAttribute("p3_name", resObj.getString("p3_name"));
                        request.setAttribute("p4_name", resObj.getString("p4_name"));
                        request.setAttribute("p5_name", resObj.getString("p5_name"));
                        request.setAttribute("p6_name", resObj.getString("p6_name"));
                        request.setAttribute("p7_name", resObj.getString("p7_name"));
                        request.setAttribute("p8_name", resObj.getString("p8_name"));
                        request.setAttribute("p9_name", resObj.getString("p9_name"));
                        request.setAttribute("p10_name", resObj.getString("p10_name"));
                        request.setAttribute("p11_name", resObj.getString("p11_name"));
                        request.setAttribute("p12_name", resObj.getString("p12_name"));

                        request.setAttribute("p1", p1);
                        request.setAttribute("p2", p2);
                        request.setAttribute("p3", p3);
                        request.setAttribute("p4", p4);
                        request.setAttribute("p5", p5);
                        request.setAttribute("p6", p6);
                        request.setAttribute("p7", p7);
                        request.setAttribute("p8", p8);
                        request.setAttribute("p9", p9);
                        request.setAttribute("p10", p10);
                        request.setAttribute("p11", p11);
                        request.setAttribute("p12", p12);


                    

                    request.getRequestDispatcher("tripdetailspage.jsp").forward(request, response);//show only
                    }
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
        todaycount=0;
        todayearnings=0;
        Date today = new Date();
        for (int i = 0; i < alltrips.size(); i++) {
            Date d = alltrips.get(i).start_Date;
            if(d.after(lastdate) || DateUtils.isSameDay(d,today)){
                filterdtrips.add(alltrips.get(i));
                todaycount++;
                todayearnings+=alltrips.get(i).price;
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
