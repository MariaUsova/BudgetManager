package budget.services;



import budget.enums.Category;
import budget.models.Transaction;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PurchaseService {
  public static final Map<Integer, Category> categoryMap = Map.of(
      1, Category.FOOD,
      2, Category.CLOTHES,
      3, Category.ENTERTAINMENT,
      4, Category.OTHER
  );

  public static void addPurchase(List<Transaction> transactions, Scanner scanner) {
    scanner.nextLine();
    showPurchaseCategory(false);
    int item = scanner.nextInt();
    addPurchase(transactions, scanner, item);
  }

  private static void addPurchase(List<Transaction> transactions, Scanner scanner, int item) {
    while(item != 5) {
      Transaction transaction = new Transaction();
      transaction.setIncome(false);

      transaction.setCategory(categoryMap.get(item));

      System.out.println();
      System.out.println("Enter purchase name:");
      scanner.nextLine();
      String purchase = scanner.nextLine();
      transaction.setName(purchase);
      System.out.println("Enter its price:");
      Double price = scanner.nextDouble();
      transaction.setAmount(price);
      transactions.add(transaction);
      System.out.println("Purchase was added!");
      scanner.nextLine();
      showPurchaseCategory(false);
      item = scanner.nextInt();
    }
  }

  public static void showPurchaseCategory(boolean toGet) {

   showPurchaseCategory();
    if (toGet) {
      System.out.println("5) All");
      System.out.println("6) Back");
    } else {
      System.out.println("5) Back");
    }
  }

  public static void showPurchaseCategory() {
    System.out.println();
    System.out.println("Choose the type of purchases");
    System.out.println("1) " + Category.FOOD);
    System.out.println("2) " + Category.CLOTHES);
    System.out.println("3) " + Category.ENTERTAINMENT);
    System.out.println("4) " + Category.OTHER);
  }

  public static void switchPurchaseCategory(List<Transaction> transactions, Scanner scanner) {
    showPurchaseCategory(true);
    int category = scanner.nextInt();
    while(category < 6) {
      if (category == 5) {
        showAllPurchases(transactions);
      } else {
        showFilteredPurchases(transactions, categoryMap.get(category));
      }
      showPurchaseCategory(true);
      category = scanner.nextInt();
    }
  }

  public static void showFilteredPurchases(List<Transaction> transactions, Category category) {
    System.out.println();
    if (transactions.isEmpty()) {
      System.out.println("The purchase list is empty");
    } else {
      List<Transaction> filteredList = transactions.stream().filter(tr -> category.equals(tr.category)).collect(Collectors.toList());
      System.out.println(category.name() + ":");

      Double sum = 0.0;
      for (Transaction tr : filteredList) {
        System.out.printf("%s $%.2f", tr.name, tr.amount);
        System.out.println();
        sum += tr.amount;
      }
      System.out.printf("Total sum: $%.2f", sum);
      System.out.println();
    }
  }

  public static void showAllPurchases(List<Transaction> transactions) {
    System.out.println();
    System.out.println("All:");
    showPurchases(getPurchaseTransactions(transactions));
  }

  private static void showPurchases(List<Transaction> purchases) {
    Double sum = 0.0;
    for (Transaction tr : purchases) {
      System.out.printf("%s $%.2f", tr.name, tr.amount);
      System.out.println();
      sum += tr.amount;
    }
    System.out.printf("Total sum: $%.2f", sum);
    System.out.println();
  }

  public static List<Transaction> getPurchaseTransactions(List<Transaction> transactions) {
    return transactions.stream().filter(tr -> !tr.isIncome).collect(Collectors.toList());
  }
}
