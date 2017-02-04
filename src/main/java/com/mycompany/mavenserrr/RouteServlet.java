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
import myclassespackage.URLsClass;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/RouteServlet"})
public class RouteServlet extends HttpServlet {
    
    ArrayList<Double> lats = new ArrayList<Double>();
    ArrayList<Double> lngs = new ArrayList<Double>();

    ArrayList<Double> glats = new ArrayList<Double>();
    ArrayList<Double> glngs = new ArrayList<Double>();
    
    public static Driver sDriver;
    
    
    Double selectedlat = 37.334818;
    Double selectedlng = -121.884886;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //initially load last ristricted route
            if(sDriver.restrictedLats.size()!=0){
                glats =  sDriver.restrictedLats;
                glngs = sDriver.restrictedLngs;
                int center = sDriver.restrictedLngs.size()/2;
                selectedlat =  sDriver.restrictedLats.get(center);
                selectedlng = sDriver.restrictedLngs.get(center);
            }
            
            
                            request.setAttribute("glats", glats);  
                            request.setAttribute("glngs", glngs);  
                            request.setAttribute("selectedlat", selectedlat);  
                            request.setAttribute("selectedlng", selectedlng);   
                            request.setAttribute("sDriver", sDriver);

            request.getRequestDispatcher("drouterestrictions.jsp").forward(request, response);//show only

        }
        catch(Exception e){
            Logger.getLogger(RouteServlet.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            out.close();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        try{    
            DataClass.checkSession(request, response);
        
        String goflag= request.getParameter("goflag");
        
        if(goflag.equals("clicked")){

            
        Double golat = Double.parseDouble(request.getParameter("lat"));
        Double golng = Double.parseDouble(request.getParameter("lng"));  
        selectedlat = Double.parseDouble(request.getParameter("selectedlat"));  
        selectedlng = Double.parseDouble(request.getParameter("selectedlng"));  

            if(glats.size()==0){//|| lats.size()==1){//lats
                lats.add(golat);
                lngs.add(golng);
                glats.add(golat);
                glngs.add(golng);
                            
                request.setAttribute("glats", glats);  
                request.setAttribute("glngs", glngs);  
                request.setAttribute("sDriver", sDriver);
                request.setAttribute("selectedlat", selectedlat);  
                request.setAttribute("selectedlng", selectedlng);  
                request.getRequestDispatcher("drouterestrictions.jsp").forward(request, response);
            }
            else {//if(lats.size()>1){//y3ny feh 2 3la l a2l
                //get list between them
               
                String u = "https://roads.googleapis.com/v1/snapToRoads?path="+glats.get(glats.size()-1)+","+glngs.get(glngs.size()-1)+"|"+golat+","+golng+"&interpolate=true&key="+URLsClass.apikey;
                JSONObject resObj = DataClass.getJSONObject(u, "");
                
                JSONArray arr = resObj.getJSONArray("snappedPoints");
                    
                for (int i = 0; i < arr.size(); i++) {
                        
                    JSONObject o = arr.getJSONObject(i).getJSONObject("location");
                    glats.add(o.getDouble("latitude"));
                    glngs.add(o.getDouble("longitude"));
                }
                
                request.setAttribute("glats", glats);  
                request.setAttribute("glngs", glngs);  
                request.setAttribute("selectedlat", selectedlat);  
                request.setAttribute("selectedlng", selectedlng);  
                request.setAttribute("sDriver", sDriver);

                request.getRequestDispatcher("drouterestrictions.jsp").forward(request, response);
            }

        }
        else if(goflag.equals("clear")){
            glats.clear();
            glngs.clear();
               
            request.setAttribute("glats", glats);  
            request.setAttribute("glngs", glngs);
            request.setAttribute("sDriver", sDriver);

            request.setAttribute("selectedlat", selectedlat);  
            request.setAttribute("selectedlng", selectedlng);  
            
            request.getRequestDispatcher("drouterestrictions.jsp").forward(request, response);
        }
        else if(goflag.equals("save")){
            
            JSONObject go = new JSONObject();
            JSONArray ar = convtoJson();
            go.put("routes", ar);
            go.put("driver_id", sDriver.id);
            JSONObject resObj = DataClass.getJSONObject(URLsClass.addristrictedroute, go.toString());
                            
                int successf = resObj.getInt("success");
                if(successf==1)
                    response.sendRedirect(request.getContextPath() + "/ManageServlet?goflag=showdriver&id="+sDriver.id);//show only
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
        
        }
            catch (Exception ex) {
                Logger.getLogger(RouteServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        
                processRequest(request, response);
            }


        
    }

    JSONArray convtoJson(){
        JSONArray arr = new JSONArray();
         for (int i = 0; i < glats.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("lat", glats.get(i));
            obj.put("lng", glngs.get(i));
            arr.add(obj);
        }
         return arr;
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
