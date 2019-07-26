package logindemo;

public class TableGroups {
    
    String groupName,groupId,status;
    int totalExpense;

    public int getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(int totalExpense) {
        this.totalExpense = totalExpense;
    }

    public TableGroups(String groupName, String groupId, String status, int totalExpense) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.status = status;
        this.totalExpense = totalExpense;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
