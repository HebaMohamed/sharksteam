/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hubberspot.jersey;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import java.awt.PageAttributes;
import java.io.Serializable;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.Location;
import javax.servlet.jsp.jstl.sql.Result;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import net.sf.json.JSONObject;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;


/**
 *
 * @author dell
 */
@ApplicationPath("/")
@Path("/test")
public class HelloWordJersey implements Serializable {
            
    String output;

    
    @GET
    @Path("/goneuroph")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getmsg(){
        
        // create new perceptron network
//        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
//        // create training set
//        DataSet trainingSet =
//        new DataSet(2, 1);
//        // add training data to training set (logical OR function)
//        trainingSet. addRow (new DataSetRow (new double[]{0, 0},
//        new double[]{0}));
//        trainingSet. addRow (new DataSetRow (new double[]{0, 1},
//        new double[]{1}));
//        trainingSet. addRow (new DataSetRow (new double[]{1, 0},
//        new double[]{1}));
//        trainingSet. addRow (new DataSetRow (new double[]{1, 1},
//        new double[]{1}));
//        // learn the training set
//        neuralNetwork.learn(trainingSet);
//        // save the trained network into file
//        neuralNetwork.save("or_perceptron.nnet");
        
        
        // load the saved network
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("or_perceptron.nnet");
        // set network input
        neuralNetwork.setInput(1, 1);
        // calculate network
        neuralNetwork.calculate();
        // get network output
        double[] networkOutput = neuralNetwork.getOutput();
        
        return Response.status(200).entity(networkOutput).build();

    }

    @GET
    @Path("/go")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getm(){
        String output = "gooooooooooooooooooooo V2" ;
        return Response.status(200).entity(output).build();
    }
    @GET
    @Path("/listenpub")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getpubwarn(){
        output = "Warning Messages :" ;
        
        
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-3341df96-949d-11e6-974e-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-197b3260-4b90-42e4-8c36-4a429952a49d");

        PubNub pubnub = new PubNub(pnConfiguration);
        
        
                 
        pubnub.addListener(new SubscribeCallback() {
        @Override
        public void status(PubNub pubnub, PNStatus status) {
 
 
            if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
                // This event happens when radio / connectivity is lost
            }
 
            else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
                // Connect event. You can do stuff like publish, and know you'll get it.
                // Or just use the connected event to confirm you are subscribed for
                // UI / internal notifications, etc
             
                //if (status.getCategory() == PNStatusCategory.PNConnectedCategory){
               
                //}
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
                
           
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                System.out.println("Warning msg from user id : "+message.getMessage().toString()+" at datetime : "+date);

                
            }
            else {
                // Message has been received on channel stored in
                // message.getSubscription()
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
        
        
        return Response.status(200).entity("Listen to pubnub msgs").build();
    }

    
        @POST 
    //@GET
    @Path("/login/{user_id}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response postStrMsg(@PathParam("user_id") String user_id, @PathParam("password") String password) {

        //ArrayList<String> arr =new  ArrayList<String>();
        JSONObject obj = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hebadb?" + "user=root&password=");
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.valueOf("SELECT * FROM monitoring_member WHERE user_id = "+user_id));
            
            int f = 0;
            String s = "logged in";
            String fullname;
            while(rs.next())
             {
                 f=1;
                 
                 fullname = rs.getString(3);
                 //arr.add(fullname);
                 obj.put("name",fullname);
             }
            
            
        } catch (Exception ex) {
            Logger.getLogger(HelloWordJersey.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not done");
        }

        
        
        return Response.status(200).entity(obj.toString()).build();
    }
    
    
    
    
    @POST 
    //@GET
    @Path("/findvehicle/{userid}/{lat}/{lng}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response postStrMsg(@PathParam("userid") String userid, @PathParam("lat") double lat, @PathParam("lng") double lng) {

//                ArrayList<MyDriver> drivers = new ArrayList<MyDriver>();
                    
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://www.db4free.net/hebadb?" + "user=heba&password=380f3e");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hebadb?" + "user=root&password=");

            System.out.println("done");
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM drivers;");
            
//            MyDriver driver = null ;
//            float mindistance = 0;
//            while(rs.next())
//             {
//                 int did = rs.getInt(1);
//                 String dname = rs.getString(2);
//                 String dcarplate = rs.getString(3);
//                 double dlat = rs.getDouble(4);
//                 double dlng = rs.getDouble(5);
//                 
//                 float dist = distFrom((float)lat, (float)lng, (float)dlat, (float)dlng);
//                 if(mindistance==0 || dist<mindistance){ //first time & every time
//                     mindistance=dist;
//                     driver=new MyDriver(did, dname, dcarplate, dlat, dlng);
//                 }                 
//             }
//            
//            if(driver!=null)
//                drivers.add(driver);

        } catch (Exception ex) {
            Logger.getLogger(HelloWordJersey.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not done");
        }

        
        
        //return Response.status(200).entity(drivers).build();
        return Response.status(200).entity("").build();

    }
    
     public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

     
}
