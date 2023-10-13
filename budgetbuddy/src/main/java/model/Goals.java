package model;
import java.util.Date;

// Class for Goals
public class Goals {
    private int id;
    private int userId;
    private String name;
    private int goalAmount;
    private int savedAmount;
    private String category;
     private String description;
    private Date date;

// Goal Constructor 
    public Goals(int id, int userId, String name, int goalAmount, int savedAmount, String category,String description, Date date) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.goalAmount = goalAmount;
        this.savedAmount = savedAmount;
        this.category = category;
        this.date = date;
    }

    //Getters and Settetrs 
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getGoalAmount(){
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount){
        this.goalAmount = goalAmount;
    }

    public int getSavedAmount(){
        return savedAmount;
    }

    public void setSavedAmount(int savedAmount){
        this.savedAmount = savedAmount;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
