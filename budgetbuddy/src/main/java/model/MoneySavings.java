package model;

public class MoneySavings {

  private String id;

  private String name;

  private String totalAmount;

  private String interest;

  private String totalMonth;

  private String userId;

  private String createdDate;

  public MoneySavings() {

  }

  public MoneySavings(String id,
                      String name,
                      String totalAmount,
                      String interest,
                      String totalMonth,
                      String userId,
                      String createdDate) {
    this.id = id;
    this.totalAmount = totalAmount;
    this.name = name;
    this.interest = interest;
    this.totalMonth = totalMonth;
    this.userId = userId;
    this.createdDate = createdDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getInterest() {
    return interest;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }

  public String getTotalMonth() {
    return totalMonth;
  }

  public void setTotalMonth(String totalMonth) {
    this.totalMonth = totalMonth;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
