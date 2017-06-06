package com.mycompany.mavenserrr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.firebase.client.Firebase;
import myclassespackage.URLsClass;
import myclassespackage.DataClass;
import myclassespackage.Driver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import myclassespackage.MonitoringMember;
import myclassespackage.Passenger;
import myclassespackage.Trip;
import myclassespackage.Vehicle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/ManageServlet"})
@MultipartConfig // for getpart
public class ManageServlet extends HttpServlet {

    public static int currentId;
    ArrayList<Driver> alldrivers = new ArrayList<Driver>();
    ArrayList<Driver> filtereddrivers = new ArrayList<Driver>();
    
    public static Driver selecteddriver;
    
    String currentDid;
    
    static ArrayList<Trip> alldrivertrips = new ArrayList<Trip>();


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
            
//            DataClass.currentMM=new MonitoringMember(1, "ki", "omjio", "in");

                        
            //just show drivers
            JSONObject obj;
                obj = DataClass.getJSONObject(URLsClass.getdrivers, "");
                getDriversData(obj);
              //  request.setAttribute("drivers", obj.getJSONArray("drivers"));
                request.setAttribute("drivers", alldrivers);  
              request.getRequestDispatcher("managedriver.jsp").forward(request, response);//show only

       

        }
        catch(Exception e)
        {// lw msh wla 7aga mn dol
            
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, e);
   
        } finally {
            out.close();
        }
    }
    
    
    void getDriversData(JSONObject obj){
        alldrivers.clear();
//        filtereddrivers.clear();
        for (int i = 0; i < obj.getJSONArray("drivers").size(); i++) {
            Driver d = new Driver(Integer.parseInt(obj.getJSONArray("drivers").getJSONObject(i).getString("did")),
                                                    obj.getJSONArray("drivers").getJSONObject(i).getString("dname"),
                                                    obj.getJSONArray("drivers").getJSONObject(i).getString("demail"),
                                                    "", "");
            d.vehicle = new Vehicle(obj.getJSONArray("drivers").getJSONObject(i).getInt("vid"));
            alldrivers.add(d);
//            filtereddrivers.add(d);
        }
    }
    
    void searchdriver(String t){
        filtereddrivers.clear();
        for (int i = 0; i < alldrivers.size(); i++) {
            String name = alldrivers.get(i).name.toLowerCase();
            if(t.equals("")){
                filtereddrivers.add(alldrivers.get(i));
            }
            else if(name.equals(t.toLowerCase()) || String.valueOf(alldrivers.get(i).id).equals(t)){
                filtereddrivers.add(alldrivers.get(i));
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
                    filtereddrivers.add(alldrivers.get(i));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try{
            DataClass.checkSession(request, response);
            
            String goflag= request.getParameter("goflag");

            if(goflag.equals("showdriver")){
                String dId= request.getParameter("id");
                currentId = Integer.parseInt(dId);
                //get Driver
                selecteddriver = getDriver(Integer.parseInt(dId)); //Driver d = getDriver(Integer.parseInt(dId));
                RouteServlet.sDriver=selecteddriver;//for routing
                getristrictedroutemap(selecteddriver);
                
                //for img
                String url;
                if(!selecteddriver.image.equals(""))
                    url = "data:image/png;base64," + selecteddriver.image;
                else
                    url = "images/admin_icon.jpg";
                
                request.setAttribute("image", url);
                
                request.setAttribute("selecteddriver", selecteddriver);
                ArrayList<Vehicle> allV = getAllVehicles();
                request.setAttribute("allV", allV);
                request.getRequestDispatcher("driverprofile.jsp").forward(request, response);
            }
            else if(goflag.equals("editdriver")){
                String dId= request.getParameter("id");
                currentId = Integer.parseInt(dId);
                //get Driver
                
                //for img
                String url;
                if(!selecteddriver.image.equals(""))
                    url = "data:image/png;base64," + selecteddriver.image;
                else
                    url = "images/admin_icon.jpg";
                request.setAttribute("image", url);
                
                selecteddriver = getDriver(Integer.parseInt(dId)); //Driver d = getDriver(Integer.parseInt(dId));
                request.setAttribute("selecteddriver", selecteddriver);
                request.getRequestDispatcher("editdriver.jsp").forward(request, response);
            }
            else if(goflag.equals("searched")){
                                
                request.setAttribute("drivers", filtereddrivers);  
              request.getRequestDispatcher("managedriver.jsp").forward(request, response);//show only
            }
            else if(goflag.equals("assignvehicle")){
            
                String vid = request.getParameter("vid");
                JSONObject resObj = DataClass.getJSONObject(URLsClass.assignvehicle+vid+"/"+selecteddriver.id+"/"+selecteddriver.vehicle.ID+"", "");
                int successf = resObj.getInt("success");
               if(successf==1)
                    response.sendRedirect(request.getContextPath() + "/ManageServlet?goflag=showdriver&id="+selecteddriver.id);
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
        }
            else if(goflag.equals("driverreport")){
            
                String did = request.getParameter("did");
                currentDid=did;
                JSONObject resObj = DataClass.getJSONObject(URLsClass.getdriveravg+did+"/", "");
                int successf = resObj.getInt("success");
               if(successf==1){
               String avgtxt = resObj.getString("avgtxt");
               int avg = resObj.getInt("avg");
               request.setAttribute("p1_name",resObj.getString("p1_name"));
               request.setAttribute("p2_name",resObj.getString("p2_name"));
               request.setAttribute("p3_name",resObj.getString("p3_name"));
               request.setAttribute("p4_name",resObj.getString("p4_name"));
               request.setAttribute("p5_name",resObj.getString("p5_name"));
               request.setAttribute("p6_name",resObj.getString("p6_name"));
               request.setAttribute("p7_name",resObj.getString("p7_name"));
               request.setAttribute("p8_name",resObj.getString("p8_name"));
               request.setAttribute("p9_name",resObj.getString("p9_name"));
               request.setAttribute("p10_name",resObj.getString("p10_name"));
               request.setAttribute("p11_name",resObj.getString("p11_name"));
               request.setAttribute("p12_name",resObj.getString("p12_name"));
               
               request.setAttribute("rates_1",resObj.getInt("rates_1"));
               request.setAttribute("rates_2",resObj.getInt("rates_2"));
               request.setAttribute("rates_3",resObj.getInt("rates_3"));
               request.setAttribute("rates_4",resObj.getInt("rates_4"));
               request.setAttribute("rates_5",resObj.getInt("rates_5"));

               JSONArray pattrens = resObj.getJSONArray("pattrens");
               
               ArrayList<String> daysnames = new ArrayList<String>();
               
               Calendar cal = Calendar.getInstance();
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_1 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_2 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_3 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_4 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_5 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_6 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());
               cal.add(Calendar.DAY_OF_YEAR, -1);
               long daysAgo_7 = cal.getTimeInMillis();
               daysnames.add(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase());

               ArrayList<Integer> p1Arr = new ArrayList <Integer>();
               ArrayList<Integer> p2Arr = new ArrayList <Integer>();
               ArrayList<Integer> p3Arr = new ArrayList <Integer>();
               ArrayList<Integer> p4Arr = new ArrayList <Integer>();
               ArrayList<Integer> p5Arr = new ArrayList <Integer>();
               ArrayList<Integer> p6Arr = new ArrayList <Integer>();
               ArrayList<Integer> p7Arr = new ArrayList <Integer>();
               ArrayList<Integer> p8Arr = new ArrayList <Integer>();
               ArrayList<Integer> p9Arr = new ArrayList <Integer>();
               ArrayList<Integer> p10Arr = new ArrayList <Integer>();
               ArrayList<Integer> p11Arr = new ArrayList <Integer>();
               ArrayList<Integer> p12Arr = new ArrayList <Integer>();
               for (int i = 0; i < 7; i++) {//init 7 days 0
                   p1Arr.add(0);                   
                   p2Arr.add(0);
                   p3Arr.add(0);
                   p4Arr.add(0);
                   p5Arr.add(0);
                   p6Arr.add(0);
                   p7Arr.add(0);
                   p8Arr.add(0);
                   p9Arr.add(0);
                   p10Arr.add(0);
                   p11Arr.add(0);
                   p12Arr.add(0);
               }
               
               
               for (int i = 0; i < pattrens.size(); i++) {
                   JSONObject pattrenobj = pattrens.getJSONObject(i);
                   int pattrenid = pattrenobj.getInt("pattrenid");
                   long pattrentimestamp = pattrenobj.getLong("timestamp");
                   if(pattrenid == 1){
                       if(pattrentimestamp>daysAgo_1){
                           p1Arr.add(0, (p1Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p1Arr.add(1, (p1Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p1Arr.add(2, (p1Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p1Arr.add(3, (p1Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p1Arr.add(4, (p1Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p1Arr.add(5, (p1Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p1Arr.add(6, (p1Arr.get(6)+1));
                       }
                   }
                   else if(pattrenid == 2){
                       if(pattrentimestamp>daysAgo_1){
                           p2Arr.add(0, (p2Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p2Arr.add(1, (p2Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p2Arr.add(2, (p2Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p2Arr.add(3, (p2Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p2Arr.add(4, (p2Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p2Arr.add(5, (p2Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p2Arr.add(6, (p2Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 3){
                       if(pattrentimestamp>daysAgo_1){
                           p3Arr.add(0, (p3Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p3Arr.add(1, (p3Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p3Arr.add(2, (p3Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p3Arr.add(3, (p3Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p3Arr.add(4, (p3Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p3Arr.add(5, (p3Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p3Arr.add(6, (p3Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 4){
                       if(pattrentimestamp>daysAgo_1){
                           p4Arr.add(0, (p4Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p4Arr.add(1, (p4Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p4Arr.add(2, (p4Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p4Arr.add(3, (p4Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p4Arr.add(4, (p4Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p4Arr.add(5, (p4Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p4Arr.add(6, (p4Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 5){
                       if(pattrentimestamp>daysAgo_1){
                           p5Arr.add(0, (p5Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p5Arr.add(1, (p5Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p5Arr.add(2, (p5Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p5Arr.add(3, (p5Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p5Arr.add(4, (p5Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p5Arr.add(5, (p5Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p5Arr.add(6, (p5Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 6){
                       if(pattrentimestamp>daysAgo_1){
                           p6Arr.add(0, (p6Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p6Arr.add(1, (p6Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p6Arr.add(2, (p6Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p6Arr.add(3, (p6Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p6Arr.add(4, (p6Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p6Arr.add(5, (p6Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p6Arr.add(6, (p6Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 7){
                       if(pattrentimestamp>daysAgo_1){
                           p7Arr.add(0, (p7Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p7Arr.add(1, (p7Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p7Arr.add(2, (p7Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p7Arr.add(3, (p7Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p7Arr.add(4, (p7Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p7Arr.add(5, (p7Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p7Arr.add(6, (p7Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 8){
                       if(pattrentimestamp>daysAgo_1){
                           p8Arr.add(0, (p8Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p8Arr.add(1, (p8Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p8Arr.add(2, (p8Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p8Arr.add(3, (p8Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p8Arr.add(4, (p8Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p8Arr.add(5, (p8Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p8Arr.add(6, (p8Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 9){
                       if(pattrentimestamp>daysAgo_1){
                           p9Arr.add(0, (p9Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p9Arr.add(1, (p9Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p9Arr.add(2, (p9Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p9Arr.add(3, (p9Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p9Arr.add(4, (p9Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p9Arr.add(5, (p9Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p9Arr.add(6, (p9Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 10){
                       if(pattrentimestamp>daysAgo_1){
                           p10Arr.add(0, (p10Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p10Arr.add(1, (p10Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p10Arr.add(2, (p10Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p10Arr.add(3, (p10Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p10Arr.add(4, (p10Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p10Arr.add(5, (p10Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p10Arr.add(6, (p10Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 11){
                       if(pattrentimestamp>daysAgo_1){
                           p11Arr.add(0, (p11Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p11Arr.add(1, (p11Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p11Arr.add(2, (p11Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p11Arr.add(3, (p11Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p11Arr.add(4, (p11Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p11Arr.add(5, (p11Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p11Arr.add(6, (p11Arr.get(6)+1));
                       }
                   }
                    else if(pattrenid == 12){
                       if(pattrentimestamp>daysAgo_1){
                           p12Arr.add(0, (p12Arr.get(0)+1));
                       }else if(pattrentimestamp>daysAgo_2){
                           p12Arr.add(1, (p12Arr.get(1)+1));
                       }else if(pattrentimestamp>daysAgo_3){
                           p12Arr.add(2, (p12Arr.get(2)+1));
                       }else if(pattrentimestamp>daysAgo_4){
                           p12Arr.add(3, (p12Arr.get(3)+1));
                       }else if(pattrentimestamp>daysAgo_5){
                           p12Arr.add(4, (p12Arr.get(4)+1));
                       }else if(pattrentimestamp>daysAgo_6){
                           p12Arr.add(5, (p12Arr.get(5)+1));
                       }else if(pattrentimestamp>daysAgo_7){
                           p12Arr.add(6, (p12Arr.get(6)+1));
                       }
                   }
               }
               
               
               request.setAttribute("p1Arr",p1Arr);
               request.setAttribute("p2Arr",p2Arr);
               request.setAttribute("p3Arr",p3Arr);
               request.setAttribute("p4Arr",p4Arr);
               request.setAttribute("p5Arr",p5Arr);
               request.setAttribute("p6Arr",p6Arr);
               request.setAttribute("p7Arr",p7Arr);
               request.setAttribute("p8Arr",p8Arr);
               request.setAttribute("p9Arr",p9Arr);
               request.setAttribute("p10Arr",p10Arr);
               request.setAttribute("p11Arr",p11Arr);
               request.setAttribute("p12Arr",p12Arr);
               
               request.setAttribute("daysnames",daysnames);
               
               request.setAttribute("dname",resObj.getString("dname"));

               request.setAttribute("avgtxt",avgtxt);
               request.setAttribute("avg",avg);
               String avgtxtlable="";
               if(avg<3)
                   avgtxtlable="label label-danger";
               else if(avg<6)
                   avgtxtlable="label label-warning";
               else if(avg<9)
                   avgtxtlable="label label-info";
               else if(avg<=12)
                   avgtxtlable="label label-success";
               request.setAttribute("avgtxtlable",avgtxtlable);



               
            request.getRequestDispatcher("monitordriverpage.jsp").forward(request, response);//show only


               
               
               }else
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
        }
            else if(goflag.equals("drivertripsreport")){
                String did = request.getParameter("did");
                currentDid=did;
                JSONObject resObj = DataClass.getJSONObject(URLsClass.getdrivertrips+did+"/", "");
                int successf = resObj.getInt("success");
                if(successf==1){
                    getTripsData(resObj);
                    request.setAttribute("trips", alldrivertrips);  
                    request.setAttribute("dname",resObj.getString("dname"));
                    request.setAttribute("acceptedcount", String.valueOf(resObj.getInt("acceptedcount")));
                    request.setAttribute("ignoredcount", String.valueOf(resObj.getInt("ignoredcount")));
                    request.setAttribute("wallet", String.valueOf(resObj.getDouble("wallet")));

                request.getRequestDispatcher("monitordrivertripspage.jsp").forward(request, response);//show only
                }
            }
            
              else if(goflag.equals("emptywallet")){
                Firebase  myFirebaseRef = new Firebase("https://sharksmapandroid-158200.firebaseio.com/");
                myFirebaseRef.child("driver").child(String.valueOf(currentDid)).child("wallet").setValue(0);
//                response.sendRedirect(request.getContextPath() + "/ManageServlet");

            }
            else if(goflag.equals("showlivetrip")){
                String dId= request.getParameter("id");
                currentId = Integer.parseInt(dId);
                //get Driver
                selecteddriver = getDriver(Integer.parseInt(dId)); 
                
                request.setAttribute("selecteddriver", selecteddriver);
                request.getRequestDispatcher("livetripjsp.jsp").forward(request, response);
            }
            
            
            
                    
            //for side menu
            if(goflag.equals("showdrivers")){
                response.sendRedirect(request.getContextPath() + "/ManageServlet");
            }
        }
        catch(NullPointerException e){
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, e);
//            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        }
        catch(Exception e){
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        processRequest(request, response);
    }

        void getTripsData(JSONObject obj) throws Exception{
        alldrivertrips.clear();
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
            
            t.from_addr=getAddress(obj.getJSONArray("trips").getJSONObject(i).getDouble("fromlat"),obj.getJSONArray("trips").getJSONObject(i).getDouble("fromlng"));
            t.to_addr=getAddress(obj.getJSONArray("trips").getJSONObject(i).getDouble("tolat"),obj.getJSONArray("trips").getJSONObject(i).getDouble("tolng"));

            
            alldrivertrips.add(t);            
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
                staticmapurl="";
            }
            return staticmapurl;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
        String hiddenflag = request.getParameter("hiddenflag");
        if(hiddenflag.equals("openadd")){
            request.getRequestDispatcher("newdriver.jsp").forward(request, response);//show only
        }
        else if(hiddenflag.equals("add")){
            
            try {
                String dname = request.getParameter("dname");
                String demail = request.getParameter("demail");
                String dpassword = request.getParameter("dpassword");
                Part p =  request.getPart("dimage");
                InputStream inputStream=null;
                if(p!=null)
                    inputStream = p.getInputStream();
                
                byte[] byteArray = new byte[(int) p.getSize()];
                // Add the bytes from the file to the array
                for(int j = 0; j < byteArray.length; j++){
                    byteArray[j] = (byte)inputStream.read();
                }
                //String str = new String(byteArray);//just for nw
                //String str = new String(byteArray, "UTF-8");
                String str = new sun.misc.BASE64Encoder().encode(byteArray);
                
                Driver d = new Driver(0, dname, demail, dpassword, str);
                //JSONObject resObj = DataClass.getJSONObject(URLsClass.adddriver+d.name+"/"+d.email+"/"+d.password+"/"+str+"/", "");
                
                //as a post request
                JSONObject go = new JSONObject();          
                go.put("name", d.name);
                go.put("email", d.email);
                go.put("password", d.password);
                go.put("img", str);
                JSONObject resObj = DataClass.getJSONObject(URLsClass.adddriver, go.toString());
                            
                int successf = resObj.getInt("success");
              
              
              
                if(successf==1)
                    response.sendRedirect(request.getContextPath() + "/ManageServlet");
//                    request.getRequestDispatcher("managedriver.jsp").forward(request, response);//show only
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
        else if(hiddenflag.equals("edit")){
            try{

                String dname = request.getParameter("dname").replaceAll("\\s",",");//replace 3shn lma yb3t fl url ynf3
                String demail = request.getParameter("demail");
                String dpassword = request.getParameter("dpassword");
                int did = Integer.parseInt(request.getParameter("did"));

                Part p =  request.getPart("dimage");
                InputStream inputStream=null;
                if(p!=null)
                    inputStream = p.getInputStream();
                
                byte[] byteArray = new byte[(int) p.getSize()];
                // Add the bytes from the file to the array
                for(int j = 0; j < byteArray.length; j++){
                    byteArray[j] = (byte)inputStream.read();
                }
//                String str = new String(byteArray);
                String str = new sun.misc.BASE64Encoder().encode(byteArray);
                Driver d = new Driver(did, dname, demail, dpassword, str);
                if(d.password.equals(""))//3shn lw fady hy3ml prob 3shn l url
                    d.password="null";
                
                //as a post request
                JSONObject go = new JSONObject();   
                go.put("id", d.id);
                go.put("name", d.name);
                go.put("email", d.email);
                go.put("password", d.password);
                go.put("img", str);
                JSONObject resObj = DataClass.getJSONObject(URLsClass.editdriver, go.toString());
                
                //JSONObject resObj = DataClass.getJSONObject(URLsClass.editdriver+d.id+"/"+d.name+"/"+d.email+"/"+d.password, "");
                int successf = resObj.getInt("success");
                if(successf==1)
//                    request.getRequestDispatcher("managedriver.jsp").forward(request, response);//show only
                    response.sendRedirect(request.getContextPath() + "/ManageServlet");

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
        else if(hiddenflag.equals("delete")){
//           String dId= request.getParameter("id");
            String dId = String.valueOf(currentId);
           try{
               JSONObject resObj = DataClass.getJSONObject(URLsClass.deletedriver+dId, "");
                int successf = resObj.getInt("success");

               if(successf==1)
                    response.sendRedirect(request.getContextPath() + "/ManageServlet");
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
        else if(hiddenflag.equals("search")){
            String srch = request.getParameter("searchtxt");
            searchdriver(srch);
            response.sendRedirect(request.getContextPath() + "/ManageServlet?goflag=searched"); //redirect to get method with searched paramenter
        }
        else if(hiddenflag.equals("sendmsg")){
            String messagetext = request.getParameter("messagetext");
            Firebase myFirebaseRef = new Firebase("https://sharksmapandroid-158200.firebaseio.com/");
            Long ts = System.currentTimeMillis();
            myFirebaseRef.child("driver").child(String.valueOf(currentDid)).child("mgrinstruction").child(String.valueOf(ts)).setValue(messagetext);

            
            response.sendRedirect(request.getContextPath() + "/ManageServlet?goflag=driverreport&did="+currentDid); //redirect to get method with searched paramenter
        }
        

        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    
    
    Driver getDriver(int id) throws Exception{
        JSONObject obj = DataClass.getJSONObject(URLsClass.getdriver+id, "");
        Driver d;
        int success = obj.getInt("success");
        
        if(success == 1){
        JSONObject dobj = obj.getJSONObject("driver");
        d = new Driver(id, dobj.getString("name"),dobj.getString("email"), "","images/img1.png");
//        d.Sharp_Uturns=dobj.getDouble("sharp_turns_freq");
//        d.Lane_Changing=dobj.getDouble("lane_changing_freq");
//        d.Harsh_Acceleration=dobj.getDouble("harch_acc_freq");
//        d.Wrong_Uturns=dobj.getDouble("wrong_u_turns_severity");
//        d.Harsh_Breaking=dobj.getDouble("harsh_breaking_freq");
//        d.Awarness_Level=dobj.getDouble("awareness_level");
        d.image=dobj.getString("image");//bytearr string
        d.logged=dobj.getBoolean("logged");
        d.intrip=dobj.getBoolean("intrip");
        
        if(d.intrip){
            d.trip=new Trip(dobj.getJSONObject("trip").getInt("tid"));
            d.trip.ilat = dobj.getJSONObject("trip").getDouble("ilat");
            d.trip.ilng = dobj.getJSONObject("trip").getDouble("ilng");
            d.trip.dlat = dobj.getJSONObject("trip").getDouble("destlat");
            d.trip.dlng = dobj.getJSONObject("trip").getDouble("destlng");
            d.trip.start_Date=new Date(dobj.getJSONObject("trip").getLong("start"));
        }
        
        
        
        //for ristricted routes level
        JSONArray arr = obj.getJSONArray("ristrictedroute");
        for (int i = 0; i < arr.size(); i++) {
            d.restrictedLats.add(arr.getJSONObject(i).getDouble("ylatitude"));
            d.restrictedLngs.add(arr.getJSONObject(i).getDouble("xlongitude"));
        }
        
        //for vehicle
        int vid = dobj.getInt("vehicle_id");
      
            d.vehicle = new Vehicle(vid);
             if(vid != 0){//0 means there is no vehicle assigned
//                d.vehicle_datetime = dobj.getString("vehicle_datetime");
                JSONObject vobj = obj.getJSONObject("vehicle");
                d.vehicle.Model = vobj.getString("model");
                d.vehicle.Color = vobj.getString("color");
                d.vehicle.Plate_number = vobj.getString("plate_number");
             }
        }
        else{
            String msg = obj.getString("msg");
            d=null;
        }

        return d;
    }


    void getristrictedroutemap(Driver d){
        String staticmapurl = "https://maps.googleapis.com/maps/api/staticmap?";
            try {
                if(d.restrictedLats.size()==0)
                    staticmapurl="";
                int centersize = d.restrictedLats.size()/2;
                String latcenter = String.valueOf(d.restrictedLats.get(centersize));
                String lngcenter = String.valueOf(d.restrictedLngs.get(centersize));
                
                staticmapurl += "center="+latcenter+","+lngcenter+"&size=2000x250&zoom=14&path=color:0x0000ff|weight:5";//&zoom=14

                for (int i = 0; i < d.restrictedLats.size(); i++) {
                    String lat = String.valueOf(d.restrictedLats.get(i));
                    String lng = String.valueOf(d.restrictedLngs.get(i));
                    staticmapurl+="|"+lat+","+lng;
                }
                staticmapurl+="&key="+URLsClass.apikey;
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            d.ristrictedrouteImg = staticmapurl;
    }

    ArrayList<Vehicle> getAllVehicles(){
        JSONObject obj;
        ArrayList<Vehicle> allvehicles = new ArrayList<Vehicle>();
        try {
            obj = DataClass.getJSONObject(URLsClass.getvehicles, "");
            allvehicles.clear();
            for (int i = 0; i < obj.getJSONArray("vehicles").size(); i++) {
                String outWorking = obj.getJSONArray("vehicles").getJSONObject(i).getString("outside_working_time_state");
                if(outWorking.equals("yes")){
                Vehicle v = new Vehicle(obj.getJSONArray("vehicles").getJSONObject(i).getInt("vehicle_id"),
                                        obj.getJSONArray("vehicles").getJSONObject(i).getString("model"), 
                                        obj.getJSONArray("vehicles").getJSONObject(i).getString("color"),
                                        0, 0, obj.getJSONArray("vehicles").getJSONObject(i).getString("outside_working_time_state"),
                                        obj.getJSONArray("vehicles").getJSONObject(i).getString("plate_number"));
                allvehicles.add(v);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allvehicles;
    }

    String getdayofweek(int num){
        String day = "";
        switch (num) {
            case 0:
                day = "Sunday";
                break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
        }
        return day;
    }
}
