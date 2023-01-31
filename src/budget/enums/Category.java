package budget.enums;

public enum Category {
  FOOD("Food"),
  CLOTHES("Clothes"),
  ENTERTAINMENT("Entertainment"),
  OTHER("Other");

  public final String label;
  Category(String label) {
    this.label = label;
  }

}
