package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expenses {
    private int id; // unique identifier for each expense
    private String expenseName;
    private double amount;
    private String category;
    private Date date;
    
    // Default categories for validation
    private static final String[] VALID_CATEGORIES = {"food", "transport", "utilities", "entertainment", "other"};

    // Constructors
    public Expenses() {
        // Default constructor
    }

    public Expenses(int id, String expenseName, double amount, String category, Date date) {
        setId(id);
        setExpenseName(expenseName);
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

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        if (expenseName == null || expenseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Expense name cannot be empty.");
        }
        this.expenseName = expenseName;
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

    public void setDate(Date date2) {
        if (date2 == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date2;
    }

    // Utility method to validate category
    private boolean isValidCategory(String category) {
        for (String validCategory : VALID_CATEGORIES) {
            if (validCategory.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Expense [id=" + id + ", expenseName=" + expenseName + ", amount=" + amount + ", category=" + category
                + ", date=" + sdf.format(date) + "]";
    }
}