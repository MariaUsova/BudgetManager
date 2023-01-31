package budget.services;

import budget.models.Transaction;
import java.util.List;

public class BalanceService {

  public static void showBalance(List<Transaction> transactions) {
    Double balance = 0.00;

    for (Transaction tr : transactions) {
      if (tr.isIncome) {
        balance += tr.amount;
      } else {
        balance -= tr.amount;
      }
    }

    if (balance < 0.00) {
      balance = 0.00;
    }
    System.out.println();
    System.out.printf("Balance: $%.2f", balance);
    System.out.println();
  }

}
