package myclassespackage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.management.monitor.Monitor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class DataClass {
    public static MonitoringMember currentMM;
    
    
    public static void startSession(MonitoringMember mm, HttpServletRequest request){
        currentMM=mm;
        HttpSession session = request.getSession(true); //true y3ny lw ms mwgod create it
        session.setAttribute("MonitoringMember", mm);
        session.setMaxInactiveInterval(30*60); // for half hour session
    }
    
    public static void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean b;
        HttpSession session = request.getSession(false);
        if(session==null)
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        else
            currentMM = (MonitoringMember) session.getAttribute("MonitoringMember");
    }
    
    public static void endSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute("MonitoringMember");
            session.invalidate();
        }
        //response.sendRedirect(request.getContextPath() + "/LoginServlet");
    }
    
    public static void displayAlert(HttpServletResponse response, HttpServletRequest request, String msg, String gopath) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();                        
        out.println("<script type=\"text/javascript\">");
        out.println("alert('"+msg+"');");
        out.println("location='"+request.getContextPath()+gopath+"';");
        out.println("</script>");
    }
        
    
    public static JSONObject getJSONObject(String url, String param ) throws Exception{
  String charset = "UTF-8"; 
  URLConnection connection = new URL(url).openConnection();
  connection.setDoOutput(true); // Triggers POST.
  connection.setRequestProperty("Accept-Charset", charset);
  connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
      
  if(!param.equals("")){ // value to do post request or "" get request
    OutputStream output = connection.getOutputStream();
    output.write(param.getBytes(charset));  
  }
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
        
    public static JSONArray getJSONArray(String url, String param ) throws Exception{
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
       JSONArray jsonArray = JSONArray.fromObject(s);
      
  return jsonArray;
  }
}
