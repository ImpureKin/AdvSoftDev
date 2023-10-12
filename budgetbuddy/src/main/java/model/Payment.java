package model;

public class Payment {

  private String name;

  private String amount;

  private String dob;

  public Payment(String name, String amount, String dob) {
    this.name = name;
    this.amount = amount;
    this.dob = dob;
  }

  public Payment() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

}
