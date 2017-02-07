/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;
import myclassespackage.DataClass;
import myclassespackage.MonitoringMember;
import myclassespackage.URLsClass;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

        request.getRequestDispatcher("loginpage.jsp").forward(request, response);
        
        }catch(Exception e){
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            String goflag= request.getParameter("goflag");
            if(goflag.equals("logout")){
                DataClass.endSession(request,response);
            }
            else{
                processRequest(request, response);
            }
        }
        catch(Exception e){
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);
            processRequest(request, response);
        }
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String hiddenflag = request.getParameter("hiddenflag");
        if(hiddenflag.equals("login")){
           String mid= request.getParameter("mid");
           String mpassword = request.getParameter("mpassword");
           try{
               JSONObject resObj = DataClass.getJSONObject(URLsClass.loginmember+mid+"/"+mpassword, "");
                int successf = resObj.getInt("success");

               if(successf==1){
                   JSONObject m = resObj.getJSONObject("member");
                   int id = m.getInt("id");
                   String name = m.getString("name");
                   String gender = m.getString("gender");
                   String lastlogin_time = m.getString("lastlogin_time");
                   String account_state = m.getString("account_state");

                   if(!account_state.equals("pending")){
                        MonitoringMember loggedMember = new MonitoringMember(id, name, "Administrator",gender);
                        loggedMember.lastlogin_time=lastlogin_time;
                        loggedMember.account_state=account_state;
                        //DataClass.currentMM=loggedMember;
                        DataClass.startSession(loggedMember,request);
                   
                        response.sendRedirect(request.getContextPath() + "/IndexServlet");
                   }
                   else{
                        DataClass.displayAlert(response,request,"Your account in pending state","/IndexServlet");
                   }
               }
                else
                {           
                    DataClass.displayAlert(response,request,resObj.getString("msg"),"/IndexServlet");
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
        
        
        

       // processRequest(request, response);
        
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static JSONObject post(String url, String param ) throws Exception{
        
        
  String charset = "UTF-8"; 
  URLConnection connection = new URL(url).openConnection();
  connection.setDoOutput(true); // Triggers POST.
  connection.setRequestProperty("Accept-Charset", charset);
  connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
      
  OutputStream output = connection.getOutputStream();
  output.write(param.getBytes(charset));

  
  InputStream response = connection.getInputStream();
  
  
  
      
  BufferedReader streamReader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
  StringBuilder responseStrBuilder = new StringBuilder();

       String inputStr;
       while ((inputStr = streamReader.readLine()) != null)
           responseStrBuilder.append(inputStr);
       
       String s = responseStrBuilder.toString();

       JSONObject jsonObject = JSONObject.fromObject(s);

       
  return jsonObject;


  }

    
    
    
    
    

}
