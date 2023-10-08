package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deductions {
    
    private int id; 
    private int userId;
    private String name;
    private double amount;
    private String category;
    private Date date;
    private String frequency;  // Added
    private String invoice_date;  // Added
    
    // Default categories for validation
    private static final String[] VALID_CAT = {"Tax", "Insurance", "Loan", "Work Related Purchase","Other"};

    // Constructors
    
    public Deductions() {
        // Default constructor
    }

    public Deductions(int id, int userId, String name, double amount, String category, Date date, String frequency, String invoice_date) {
        setId(id);
        setUserId(userId);
        setname(name);
        setAmount(amount);
        setCategory(category);
        setDate(date);
        setFrequency(frequency);  // Initialize frequency
        setInvoiceDate(invoice_date);  // Initialize invoice_date
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

    public int getUserId() { // New getter for userId
        return userId;
    }

    public void setUserId(int userId) { // New setter for userId
        if (userId < 0) {
            throw new IllegalArgumentException("UserID cannot be negative.");
        }
        this.userId = userId;
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getInvoiceDate() {
        return invoice_date;
    }

    public void setInvoiceDate(String invoice_date) {
        this.invoice_date = invoice_date;
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

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(this.date);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Deduction [id=" + id + ", userId=" + userId + ", name=" + name + ", amount=" + amount + ", category=" + category
                + ", date=" + sdf.format(date) + ", frequency=" + frequency + ", invoice_date=" + invoice_date + "]";
    }
}