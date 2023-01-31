package budget.services;

import budget.enums.Category;
import budget.models.Transaction;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class FileService {
  private final String INCOME = "INCOME";

  public static void savePurchasesToFile(List<Transaction> transactions) {
    File file = new File("purchases.txt");
    try (PrintWriter printer = new PrintWriter(file)) {

      boolean createdNew = file.createNewFile();
      for (Transaction tr : transactions) {
        String formattedName = tr.name.replace(" ", "_");
        if (tr.isIncome) {
          printer.printf("%d %s %.2f\n", 1, formattedName, tr.amount);
        } else {
          printer.printf("%d %s %.2f %s\n", 0, formattedName, tr.amount, tr.category.name());
        }
      }
    } catch (IOException e) {
      System.out.printf("Exception %s", e.getMessage());
    }

    System.out.println();
    System.out.println("Purchases were saved!");
  }

  public static List<Transaction> loadTransactions(List<Transaction> transactions) {
    File file = new File("purchases.txt");
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNext()) {
        Transaction transaction = new Transaction();
        int isIncome = scanner.nextInt();

        if (isIncome == 1) {
          transaction.setIncome(true);
          String name = scanner.next();
          transaction.setName(formatToTransactionName(name));
          Double amount = scanner.nextDouble();
          transaction.setAmount(amount);
        } else {
          transaction.setIncome(false);
          String name = scanner.next();
          transaction.setName(formatToTransactionName(name));
          Double amount = scanner.nextDouble();
          transaction.setAmount(amount);
          Category category = Category.valueOf(scanner.next());
          transaction.setCategory(category);
        }
        transactions.add(transaction);
        scanner.nextLine();

      }
    } catch (IOException e) {
      System.out.printf("Exception %s", e.getMessage());
    }

    System.out.println();
    System.out.println("Purchases were loaded!");
    return transactions;
  }

  private static String formatToTransactionName(String str) {
    return str.replace("_", " ");
  }
  private static PrintWriter setPrintWriter() {
    File file = new File("purchases.txt");
    try (PrintWriter printer = new PrintWriter(file)) {

      boolean createdNew = file.createNewFile();

    } catch (IOException e) {
      System.out.printf("Exception %s", e.getMessage());
    }
    return null;
  }

}
