/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenserrr;

import com.google.gson.JsonElement;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNHistoryItemResult;
import com.pubnub.api.models.consumer.history.PNHistoryResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Callback;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myclassespackage.EventWarning;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author dell
 */
@WebServlet(name = "EventServlet", urlPatterns = {"/EventServlet"})
public class EventServlet extends HttpServlet {

        
    ArrayList<EventWarning> warnings = new ArrayList<EventWarning>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet EventServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EventServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
//////////////////////////////////////////////////////////////////////////////////////////////////////

        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-a92c9e70-e683-11e6-b3b8-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-b04f5dff-3f09-4dc6-8b4e-58034b4b85bb");

        PubNub pubnub = new PubNub(pnConfiguration);
        
        pubnub.history()
        .channel("warningchannel") // where to fetch history from
        .count(100) // how many items to fetch
        .async(new PNCallback<PNHistoryResult>() {
            @Override
            public void onResponse(PNHistoryResult result, PNStatus status) {
    
                if (!status.isError()) { 
                    //DatabaseHelper db = DatabaseHelper.getInstance(service); 
                    List<PNHistoryItemResult> messages = result.getMessages(); 
                    for (PNHistoryItemResult iR:messages) { 
                        try { 
                            JsonElement n = iR.getEntry(); 
                            String objstr = n.getAsString(); 
                            
                        }catch (Exception e){
                            e.printStackTrace();
                        } 
                    } 
                } 
            }

        });



    }


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int x = 0;
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
    }// </editor-fold>

}
