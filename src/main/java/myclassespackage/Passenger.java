package myclassespackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Passenger {
    
    public int ID;
    String Password;
    public String FullName;
    String Gender;
    int Age;
    public String Phone;
    public String Relative_phone;
    String Language;
    String UserName;
    
    public Passenger(int id){
        this.ID=id;
    }
    public Passenger(int id,String pass,String name,String gender,int age,String phone,String R_phone,String language,String U_name)
    {
        ID=id;
        Password=pass;
        FullName=name;
        Gender=gender;
        Age=age;
        Phone=phone;
        Relative_phone=R_phone;
        Language=language;
        UserName=U_name;
        
        
    }
    
    
}
