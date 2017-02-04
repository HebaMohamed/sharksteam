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
    
    int ID;
    String Password;
    String FullName;
    String Gender;
    int Age;
    int Phone;
    int Relative_phones;
    String Language;
    String UserName;
    
    public Passenger(int id){
        this.ID=id;
    }
    public Passenger(int id,String pass,String name,String gender,int age,int phone,int R_phone,String language,String U_name)
    {
        ID=id;
        Password=pass;
        FullName=name;
        Gender=gender;
        Age=age;
        Phone=phone;
        Relative_phones=R_phone;
        Language=language;
        UserName=U_name;
        
        
    }
    
    
}
