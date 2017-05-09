package myclassespackage;


import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import myclassespackage.Driver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Trip {
public int trip_ID;
public Date start_Date;
public Date end_Date;
public double price;
double longtude;
double lattitude;
public String comment;
public int rating;

public String from_addr;
public String to_addr;

public String status;

public Trip(int id)
{
    trip_ID=id;
}
public Trip(int id,String s_date,String e_date,double price, String comment ,int rating)
{
    this.trip_ID=id;
//    Timestamp ts1 = new Timestamp(Long.parseLong(s_date));
//    Timestamp ts2 = new Timestamp(Long.parseLong(e_date));//new Date(ts1.getTime())

    this.start_Date=new Date(Long.valueOf(s_date));//todatetime(s_date);//lw string bs hna timestamp
    this.end_Date=new Date(Long.valueOf(e_date));//todatetime(e_date);
    this.price=price;
    this.comment=comment;
    this.rating=rating;
}

Date todatetime(String d){
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2017-01-24 06:00:00.0
    try {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(d);
        return date;
//        System.out.println("mydate -> "+mydate);
//        System.out.println("year   -> "+mydate.get(Calendar.YEAR));
//        System.out.println("month  -> "+mydate.get(Calendar.MONTH));
//        System.out.println("dom    -> "+mydate.get(Calendar.DAY_OF_MONTH));
//        System.out.println("dow    -> "+mydate.get(Calendar.DAY_OF_WEEK));
//        System.out.println("hour   -> "+mydate.get(Calendar.HOUR));
//        System.out.println("minute -> "+mydate.get(Calendar.MINUTE));
//        System.out.println("second -> "+mydate.get(Calendar.SECOND));
//        System.out.println("milli  -> "+mydate.get(Calendar.MILLISECOND));
//        System.out.println("ampm   -> "+mydate.get(Calendar.AM_PM));
//        System.out.println("hod    -> "+mydate.get(Calendar.HOUR_OF_DAY));
        
    } catch (ParseException ex) {
        Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
            return null;
    }
}
public Passenger p;
public Driver d ;

public String staticmapurl;




    public String getStartdate(){
    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String s = formatter.format(start_Date);
    return s;
    }
    public String getEnddate(){
    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String s = formatter.format(end_Date);
    return s;
    }
    
}