package model;

public class TotalUserSavings {
    private int userId;
    private int totalSavings;
    private int totalGoalSavings;
    private int totalSaved;

    // Constructor
    public TotalUserSavings(int userId, int totalSavings, int totalGoalSavings, int totalSaved){
        this.userId = userId;
        this.totalSavings = totalSavings;
        this.totalGoalSavings = totalGoalSavings;
        this.totalSaved = totalSaved;
    }

    //Getters and Setters
    public int getUserId(){
        return userId;
    }

    public void setUserID(int userId){
        this.userId = userId;
    }

    public int getTotalSavings(){
        return totalSavings;
    }

    public void setTotalSavings(int totalSavings){
        this.totalSavings = totalSavings;
    }

    public int getTotalGoalSavings(){
        return totalGoalSavings;
    }

    public void setTotalGoalSavings(int totalGoalSavings){
        this.totalGoalSavings = totalGoalSavings;
    }

    public int getTotalSaved(){
        return totalSaved;
    }

    public void setTotalSaved(int totalSaved){
        this.totalSaved = totalSaved;
    }

}
