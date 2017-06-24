package myclassespackage;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Vehicle {
public int ID;
public String Model;
public String Color;
public double Longtude;
public double Attitude;
public boolean Outside_working;
public String Plate_number;
public int status;

public ArrayList<Driver> lastdrivers = new ArrayList<Driver>();

public Vehicle(int id)
{
    ID=id;
}
public Vehicle(int id,String model,String color,double lngtude,double atitude,boolean outside_working, String plate_number)
{
    ID=id;
    Model=model;
    Color=color;
    Longtude=lngtude;
    Attitude=atitude;
    Outside_working=outside_working;
    Plate_number=plate_number;
            
}
}
