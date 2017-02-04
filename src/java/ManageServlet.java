/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import myclassespackage.URLsClass;
import myclassespackage.DataClass;
import myclassespackage.Driver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import myclassespackage.Vehicle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.Session;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


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
            
                    
            //for side menu
            if(goflag.equals("showdrivers")){
                response.sendRedirect(request.getContextPath() + "/ManageServlet");
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
                    request.getRequestDispatcher("managedriver.jsp").forward(request, response);//show only
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
                Driver d = new Driver(1, dname, demail, dpassword, str);
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
        d.Sharp_Uturns=dobj.getDouble("sharp_turns_freq");
        d.Lane_Changing=dobj.getDouble("lane_changing_freq");
        d.Harsh_Acceleration=dobj.getDouble("harch_acc_freq");
        d.Wrong_Uturns=dobj.getDouble("wrong_u_turns_severity");
        d.Harsh_Breaking=dobj.getDouble("harsh_breaking_freq");
        d.Awarness_Level=dobj.getDouble("awareness_level");
        d.image=dobj.getString("image");//bytearr string
        
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
                d.vehicle_datetime = dobj.getString("vehicle_datetime");
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

}
