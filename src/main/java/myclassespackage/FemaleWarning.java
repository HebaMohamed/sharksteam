/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclassespackage;

/**
 *
 * @author dell
 */
public class FemaleWarning {
    public Long timestamp;
    public double lat;
    public double lng;
    public int tid;
    public String datetxt;
    
    public Driver d;
    public Passenger p;
    
    public FemaleWarning(){
        d = new Driver(0);
        p = new Passenger(0);
    }
}
