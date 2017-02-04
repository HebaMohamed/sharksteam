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
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/ManageVehicleServlet"})
public class ManageVehicleServlet extends HttpServlet {
    
    int currentId;
    ArrayList<Vehicle> allvehicles = new ArrayList<Vehicle>();
    ArrayList<Vehicle> filteredvehicles = new ArrayList<Vehicle>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //for ex
//            DataClass.currentMM=new MonitoringMember(1, "ki", "omjio", "in");
            
            //just show vehicles
//            JSONObject obj;
            try {
//                obj = DataClass.getJSONObject(URLsClass.getvehicles, "");
//                getVehiclesData(obj);
//                request.setAttribute("vehicles", allvehicles);  
//              request.getRequestDispatcher("managevehicle.jsp").forward(request, response);//show only
                gohome(request,response);

            } catch (Exception ex) {
                Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            out.close();
        }
    }
    
    void gohome(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getvehicles, "");
                    getVehiclesData(obj);
                    request.setAttribute("vehicles", allvehicles);  
                    request.getRequestDispatcher("managevehicle.jsp").forward(request, response);//show only
    }
        
    void getVehiclesData(JSONObject obj){
        allvehicles.clear();
        for (int i = 0; i < obj.getJSONArray("vehicles").size(); i++) {
            Vehicle v = new Vehicle(obj.getJSONArray("vehicles").getJSONObject(i).getInt("vehicle_id"),
                                    obj.getJSONArray("vehicles").getJSONObject(i).getString("model"), 
                                    obj.getJSONArray("vehicles").getJSONObject(i).getString("color"),
                                    0, 0, obj.getJSONArray("vehicles").getJSONObject(i).getString("outside_working_time_state"),
                                    obj.getJSONArray("vehicles").getJSONObject(i).getString("plate_number"));
            allvehicles.add(v);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try{
            DataClass.checkSession(request, response);
            
            String goflag= request.getParameter("goflag");
            if(goflag.equals("showvehicle")){
                String dId= request.getParameter("id");
                currentId = Integer.parseInt(dId);
                
                  //get Vehicle
                Vehicle v = getVehicle(Integer.parseInt(dId));
                request.setAttribute("selectedvehicle", v);
                request.getRequestDispatcher("vehicleprofile.jsp").forward(request, response);
            }
            else if(goflag.equals("searched")){
                request.setAttribute("vehicles", filteredvehicles);  
                request.getRequestDispatcher("managevehicle.jsp").forward(request, response);//show only
            }
            else if(goflag.equals("addvehicle")){
                request.getRequestDispatcher("newvehicle.jsp").forward(request, response);//show only
            }
            else if(goflag.equals("editvehicle")){
                String dId= request.getParameter("id");
                currentId = Integer.parseInt(dId);
                  //get Vehicle
                Vehicle v = getVehicle(Integer.parseInt(dId));
                request.setAttribute("selectedvehicle", v);
                request.getRequestDispatcher("editvehicle.jsp").forward(request, response);
            }
            
        }
        catch(Exception e){
            Logger.getLogger(ManageServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String hiddenflag = request.getParameter("hiddenflag");
        if(hiddenflag.equals("search")){
            String srch = request.getParameter("searchtxt");
            searchvehicle(srch);
            response.sendRedirect(request.getContextPath() + "/ManageVehicleServlet?goflag=searched"); //redirect to get method with searched paramenter
        }
        else if(hiddenflag.equals("add")){
                            
            try{
            String vmodel = request.getParameter("vmodel");
            String vcolor = request.getParameter("vcolor");
            String vplatenumber = request.getParameter("vplatenumber");
            
            JSONObject resObj = DataClass.getJSONObject(URLsClass.addvehicle+vmodel+"/"+vcolor+"/"+vplatenumber+"/", "");

            int successf = resObj.getInt("success");     
            if(successf==1){
                //request.getRequestDispatcher("managevehicle.jsp").forward(request, response);//show only
//                response.sendRedirect(request.getContextPath() + "/ManageVehicleServlet");
                gohome(request,response);
            }
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
            String vmodel = request.getParameter("vmodel");
            String vcolor = request.getParameter("vcolor");
            String vplatenumber = request.getParameter("vplatenumber");
            
            JSONObject resObj = DataClass.getJSONObject(URLsClass.editvehicle+currentId+"/"+vmodel+"/"+vcolor+"/"+vplatenumber+"/", "");

            int successf = resObj.getInt("success");     
            if(successf==1){
                gohome(request,response);
            }
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
            String vId = String.valueOf(currentId);
           try{
               JSONObject resObj = DataClass.getJSONObject(URLsClass.deletevehicle+vId, "");
                int successf = resObj.getInt("success");

               if(successf==1)
                    gohome(request, response);
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

        
        
        
//        processRequest(request, response);
    }
    
        void searchvehicle(String t){
        filteredvehicles.clear();
        for (int i = 0; i < allvehicles.size(); i++) {
            String model = allvehicles.get(i).Model.toLowerCase();
            if(t.equals("")){
                filteredvehicles.add(allvehicles.get(i));
            }
            else if(model.equals(t.toLowerCase()) || String.valueOf(allvehicles.get(i).ID).equals(t)){
                filteredvehicles.add(allvehicles.get(i));
            }
            else if(allvehicles.get(i).Plate_number.equals(t.toLowerCase())){
                filteredvehicles.add(allvehicles.get(i));
            }
            else{
                String[] splitStr = model.split("\\s+");
                int f = 0; //to add only oce
                for (int j = 0; j < splitStr.length; j++) {
                    if(splitStr[j].toLowerCase().equals(t.toLowerCase())){
                        f=1;
                    }
                }
                if(f==1)
                    filteredvehicles.add(allvehicles.get(i));
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
        Vehicle getVehicle(int id) throws Exception{
        JSONObject obj = DataClass.getJSONObject(URLsClass.getvehicle+id, "");
        Vehicle v;
        int success = obj.getInt("success");
        
        if(success == 1){
        JSONObject vobj = obj.getJSONObject("vehicle");
        String ow = "";
        if(vobj.getString("outside_working_time_state").equals("yes"))
            ow="Outside working time state";
        else
            ow="No outside working time state";
        
        v = new Vehicle(vobj.getInt("vehicle_id"),
                                    vobj.getString("model"), 
                                    vobj.getString("color"),
                                    0, 0, ow,
                                    vobj.getString("plate_number"));
        
        }
        else{
            String msg = obj.getString("msg");
            v=null;
        }

        return v;
    }

}
