package com.mycompany.mavenserrr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.firebase.client.Firebase;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import myclassespackage.EventWarning;
import myclassespackage.FemaleWarning;
import myclassespackage.URLsClass;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/FemaleServlet"})
public class FemaleServlet extends HttpServlet {

        
    ArrayList<FemaleWarning> warnings = new ArrayList<FemaleWarning>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
                request.getRequestDispatcher("femalepage.jsp").forward(request, response);
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
                
//                gohome(request, response);
                String goflag= request.getParameter("goflag");

                if(goflag.equals("showevent")){
                    String id= request.getParameter("id");
                    
                    JSONObject obj = DataClass.getJSONObject(URLsClass.getfemaleevent+id, "");
                    FemaleWarning f = new FemaleWarning();
                    f.timestamp=Long.parseLong(id);
                    f.lat=obj.getDouble("lat");
                    f.lng=obj.getDouble("lng");
                    f.d.id = obj.getInt("did");
                    f.d.name = obj.getString("dname");
                    f.p.ID = obj.getInt("pid");
                    f.p.FullName = obj.getString("pname");
                    f.v.ID = obj.getInt("vid");
                    
                    JSONObject nd = obj.getJSONObject("neard");
                    int nid = nd.getInt("id");
                    String nname = nd.getString("name");

                    Driver neardriver = new Driver (nid);
                    neardriver.name=nname;

//                    f.d.name = "mr x";
//                    f.p.FullName = "miss y";
                    
                    request.setAttribute("warning", f);
                    request.setAttribute("neardriver", neardriver);

                    request.getRequestDispatcher("femaleeventpage.jsp").forward(request, response);
                }
                else if(goflag.equals("sendhelp")){
                    String id= request.getParameter("id");
                    String vid= request.getParameter("vid");
                    
                Firebase  myFirebaseRef = new Firebase("https://sharksmapandroid-158200.firebaseio.com/");
                myFirebaseRef.child("driver").child(String.valueOf(id)).child("warninghelp").child("vid").setValue(vid);
                myFirebaseRef.child("driver").child(String.valueOf(id)).child("warninghelp").child("did").setValue(id);

                }
                
            } catch (Exception ex) {
                Logger.getLogger(TripServlet.class.getName()).log(Level.SEVERE, null, ex);
                gohome(request, response);
            }
            
    }

    void gohome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {   
            JSONObject obj;
                    obj = DataClass.getJSONObject(URLsClass.getfemaleevents, "");
                    getWarningssData(obj);
                    request.setAttribute("warnings", warnings);  
            
                    request.getRequestDispatcher("femalepage.jsp").forward(request, response);//show only
            } catch (NullPointerException ex) {
                Logger.getLogger(FemaleServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + "/LoginServlet");

            } catch (Exception ex) {
                Logger.getLogger(FemaleServlet.class.getName()).log(Level.SEVERE, null, ex);

            } 
    }
    
    void getWarningssData(JSONObject obj){
        warnings.clear();
        for (int i = 0; i < obj.getJSONArray("warning").size(); i++) {
            FemaleWarning f = new FemaleWarning();
            f.timestamp=obj.getJSONArray("warning").getJSONObject(i).getLong("timestamp");
            f.tid=(int)obj.getJSONArray("warning").getJSONObject(i).getInt("tid");
            f.lat=obj.getJSONArray("warning").getJSONObject(i).getDouble("lat");
            f.lng=obj.getJSONArray("warning").getJSONObject(i).getDouble("lng");
            f.datetxt=getdatetxt(f.timestamp);
            warnings.add(f);
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