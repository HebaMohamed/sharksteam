/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pubnub.api.*;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
@WebServlet(urlPatterns = {"/PubServlet"})
public class PubServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
        
        
        try{
        
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-3341df96-949d-11e6-974e-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-197b3260-4b90-42e4-8c36-4a429952a49d");

        PubNub pubnub = new PubNub(pnConfiguration);
        

        
         pubnub.addListener(new SubscribeCallback() {
        @Override
        public void status(PubNub pubnub, PNStatus status) {
 
 
            if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
                // This event happens when radio / connectivity is lost
                int x= 0;
            }
 
            else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
 
                // Connect event. You can do stuff like publish, and know you'll get it.
                // Or just use the connected event to confirm you are subscribed for
                // UI / internal notifications, etc
             
                if (status.getCategory() == PNStatusCategory.PNConnectedCategory){
                    pubnub.publish().channel("awesomeChannel").message("hello!!").async(new PNCallback<PNPublishResult>() {
                        @Override
                        public void onResponse(PNPublishResult result, PNStatus status) {
                            // Check whether request successfully completed or not.
                            if (!status.isError()) {
 
                                // Message successfully published to specified channel.
                                int x = 1;
                            }
                            // Request processing failed.
                            else {
                                
                                int x = 0;
 
                                // Handle message publish error. Check 'category' property to find out possible issue
                                // because of which request did fail.
                                //
                                // Request can be resent using: [status retry];
                            }
                        }
                    });
                }
            }
            else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {
 
                // Happens as part of our regular operation. This event happens when
                // radio / connectivity is lost, then regained.
            }
            else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {
 
                // Handle messsage decryption error. Probably client configured to
                // encrypt messages and on live data feed it received plain text.
            }
        }
 
        @Override
        public void message(PubNub pubnub, PNMessageResult message) {
            // Handle new message stored in message.message
            if (message.getChannel() != null) {
                // Message has been received on channel group stored in
                // message.getChannel()
                //System.out.println("msgggggg "+message.getMessage().toString());
                
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                System.out.println("Warning msg from user id : "+message.getMessage().toString()+" at datetime : "+date);

            }
            else {
                // Message has been received on channel stored in
                // message.getSubscription()
                int y = 1;

            }
 
            /*
                log the following items with your favorite logger
                    - message.getMessage()
                    - message.getSubscription()
                    - message.getTimetoken()
            */
        }
 
        @Override
        public void presence(PubNub pubnub, PNPresenceEventResult presence) {
 
        }
    });
 
    pubnub.subscribe().channels(Arrays.asList("awesomeChannel")).execute();

        
        
        }
        catch(Exception e){
            Logger.getLogger(PubServlet.class.getName()).log(Level.SEVERE, null, e);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
