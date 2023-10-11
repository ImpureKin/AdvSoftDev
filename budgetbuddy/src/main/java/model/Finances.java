package model;

// Class for Finances 
public class Finances {
    private int userId;
    private int totalIncome;
    private int totalDeductions; 
    private int totalExpenses;
    private int totalSavings;
    
//Finances Constructor 
    public Finances(int userId, int totalIncome, int totalDeductions, int totalExpenses, int totalSavings){
        this.userId = userId;
        this.totalIncome = totalIncome;
        this.totalDeductions = totalDeductions;
        this.totalExpenses = totalExpenses;
        this.totalSavings = totalSavings;
    }

//Getters and Setters
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome){
        this.totalIncome = totalIncome;
    }

    public int getTotalDeductions(){
        return totalDeductions;
    }

    public void setTotalDeductions(int totalDeductions){
        this.totalDeductions = totalDeductions;
    }

    public int getTotalExpenses(){
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses){
        this.totalExpenses = totalExpenses;
    }

    public int getTotalSavings(){
        return totalSavings;
    }

    public void setTotalSavings(int totalSavings){
        this.totalSavings = totalSavings;
    }

}
