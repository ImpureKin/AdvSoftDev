package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deductions {
    
    private int id; 
    private String name;
    private double amount;
    private String category;  // Examples: "Tax", "Insurance", "Loan"
    private Date date;
    
    // Default categories for validation
    private static final String[] VALID_CAT = {"Tax", "Insurance", "Loan", "Work Related Purchase","Other"};

    // Constructors
    
    public Deductions() {
        // Default constructor
    }

    public Deductions(int id, String name, double amount, String category, Date date) {
        setId(id);
        setname(name);
        setAmount(amount);
        setCategory(category);
        setDate(date);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative.");
        }
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Deduction name cannot be empty.");
        }
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be greater than 0.");
        }
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || !isValidCategory(category)) {
            throw new IllegalArgumentException("Invalid category provided.");
        }
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date;
    }

    // Utility method to validate category

    private boolean isValidCategory(String category) {
        for (String validcategory : VALID_CAT) {
            if (validcategory.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Deduction [id=" + id + ", name=" + name + ", amount=" + amount + ", category=" + category
                + ", date=" + sdf.format(date) + "]";
    }
}