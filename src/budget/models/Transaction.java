package budget.models;

import budget.enums.Category;

public class Transaction {
  public String name;
  public Double amount;

  public Boolean isIncome;
  public Category category;

  public Transaction() {
  }

  public Transaction(String name, Double amount, Boolean isIncome, Category category) {
    this.name = name;
    this.amount = amount;
    this.isIncome = isIncome;
    this.category = category;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public void setIncome(Boolean income) {
    isIncome = income;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public Double getAmount() {
    return amount;
  }

  public Boolean getIncome() {
    return isIncome;
  }

  public Category getCategory() {
    return category;
  }
}
