/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindemo;

/**
 *
 * @author 16101197
 */
public class TableRequest {
    String frndName,myUserid,frndUserid,status;

    public TableRequest(String frndName, String myUserid, String frndUserid, String status) {
        this.frndName = frndName;
        this.myUserid = myUserid;
        this.frndUserid = frndUserid;
        this.status = status;
    }


    public String getName() {
        return frndName;
    }

    public void setName(String name) {
        frndName = name;
    }

    public String getMyUserid() {
        return myUserid;
    }

    public void setMyUserid(String myUserid) {
        this.myUserid = myUserid;
    }

    public String getFrndUserid() {
        return frndUserid;
    }

    public void setFrndUserid(String frndUserid) {
        this.frndUserid = frndUserid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
