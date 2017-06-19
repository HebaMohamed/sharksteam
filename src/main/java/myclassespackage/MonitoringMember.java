package myclassespackage;

import java.text.Format;
import java.text.SimpleDateFormat;
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
public class MonitoringMember {
    public String name;
    public int id;    
    public String position;
    public String gender;
    public String lastlogin_time;
    public String account_state;

    public MonitoringMember(){}
    
    public MonitoringMember(int id, String name, String position, String gender, long lastlogin){
        this.name=name;
        this.id=id;
        this.position=position;
        this.gender=gender;
        
        Date lastdate = new Date(lastlogin);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lastlogin_time = formatter.format(lastdate);
    }
    
    
}
