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
public class TableGroupEdit {
    String groupId,userId,name;
    int owes,gets,total;

    public TableGroupEdit(String groupId, String userId, int gets, int owes, String name, int total) {
        this.groupId = groupId;
        this.userId = userId;
        this.name = name;
        this.owes = owes;
        this.gets = gets;
        this.total = total;
        //System.out.println(userId+" "+name+" "+this.total);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwes() {
        return owes;
    }

    public void setOwes(int owes) {
        this.owes = owes;
    }

    public int getGets() {
        return gets;
    }

    public void setGets(int gets) {
        this.gets = gets;
    }
    
    
}
