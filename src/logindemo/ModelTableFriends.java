/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

/**
 *
 * @author Nakhla
 */
public class ModelTableFriends {
    String name,userid,owe,gets;

    public ModelTableFriends(String name, String userid,String owe,String gets) {
        this.name = name;
        this.userid = userid;
        this.owe = owe;
        this.gets = gets;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getOwe() {
        return owe;
    }

    public void setOwe(String owe) {
        this.owe = owe;
    }
    
    public String getGets() {
        return gets;
    }

    public void setGets(String gets) {
        this.gets = gets;
    }
    
}
