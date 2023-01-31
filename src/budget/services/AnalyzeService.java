package budget.services;

import budget.enums.Category;
import budget.models.Transaction;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnalyzeService {

  public static void showAnalyzeMenu() {
    System.out.println();
    System.out.println("How do you want to sort?");
    System.out.println("1) Sort all purchases");
    System.out.println("2) Sort by type");
    System.out.println("3) Sort certain type");
    System.out.println("4) Back");
  }

  public static void pickAnalyzeMenu(List<Transaction> transactions, Scanner scanner) {
    showAnalyzeMenu();
    int value = scanner.nextInt();
    while (value < 4) {
      switch(value) {
        case 1: sortAll(transactions); break;
        case 2: sortByType(transactions); break;
        case 3: sortCertainType(transactions, scanner); break;
      }
      showAnalyzeMenu();
      value = scanner.nextInt();
    }

  }

  public static void sortAll(List<Transaction> transactions) {
    List<Transaction> purchases = PurchaseService.getPurchaseTransactions(transactions);
    if (purchases.isEmpty()) {
      System.out.println();
      System.out.println("The purchase list is empty!");
      return;
    }

    purchases.sort(Comparator.comparing(Transaction::getAmount).reversed());
    PurchaseService.showAllPurchases(purchases);
  }

  public static void sortByType(List<Transaction> transactions) {
    List<Transaction> purchases = PurchaseService.getPurchaseTransactions(transactions);
    Map<Category, Double> purchaseByCategory = new HashMap<>();
    purchaseByCategory.put(Category.FOOD, 0.0);
    purchaseByCategory.put(Category.CLOTHES, 0.0);
    purchaseByCategory.put(Category.ENTERTAINMENT, 0.0);
    purchaseByCategory.put(Category.OTHER, 0.0);
    Double totalSum = 0.0;
    for (Transaction purchase : purchases) {
      Category category = purchase.getCategory();
      if (purchaseByCategory.containsKey(category)) {
        Double sum = purchaseByCategory.get(category);
        purchaseByCategory.put(category, sum + purchase.getAmount());
      } else {
        purchaseByCategory.put(category, purchase.getAmount());
      }
      totalSum += purchase.getAmount();
    }

    showSortedByType(purchaseByCategory, totalSum);
  }

  public static void sortCertainType(List<Transaction> transactions, Scanner scanner) {
    PurchaseService.showPurchaseCategory();
    int category = scanner.nextInt();
    List<Transaction> purchases = PurchaseService.getPurchaseTransactions(transactions);
    purchases.sort(Comparator.comparing(Transaction::getAmount).reversed());
    PurchaseService.showFilteredPurchases(purchases, PurchaseService.categoryMap.get(category));
  }

  private static void showSortedByType(Map<Category, Double> purchases, Double totalSum) {
    System.out.println();
    System.out.println("Types:");
    purchases.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(val -> System.out.printf("%s - $%.2f%n", val.getKey().label, val.getValue()));
    System.out.printf("Total sum: $%.2f", totalSum);
    System.out.println();
  }

}
