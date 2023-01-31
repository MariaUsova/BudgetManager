package budget.services;

import budget.models.Transaction;
import java.util.List;
import java.util.Scanner;

public class IncomeService {

  public static void addIncome(List<Transaction> transactions, Scanner scanner) {
    System.out.println();
    System.out.println("Enter income:");
    Double income = scanner.nextDouble();
    Transaction transaction = new Transaction("Income", income, true, null);
    transactions.add(transaction);
    System.out.println("Income was added!");
  }


}
