package myclassespackage;

import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Driver {
    
    public int id;
    public String name;
    public String email;
    public String password;
    public String image;//byte array string
    
    public String avgtxt;
    public int avg;
    public int lastavg;

    public boolean logged;
    public boolean intrip;


    
    
    public Driver (int id){
        this.id = id;
    }
    
    public Driver (int id, String name, String email, String password, String image){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.image=image;
    }
    
    
        
    public double Sharp_Uturns;
    public double Lane_Changing;
    public double Harsh_Acceleration;
    public double Last_trip_Behaviour_Map;
    public double Wrong_Uturns;
    public double Harsh_Breaking;
    public double Awarness_Level;
    
    public Vehicle vehicle;
    public String vehicle_datetime;
    
    public ArrayList<Integer> pattrenscount = new ArrayList<Integer>();//optional
    
    public Trip trip;

    public ArrayList<Double> restrictedLats = new ArrayList<Double>();
    public ArrayList<Double> restrictedLngs = new ArrayList<Double>();
    
    public String ristrictedrouteImg;
}

