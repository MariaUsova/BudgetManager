package budget;

import budget.models.Transaction;
import budget.services.AnalyzeService;
import budget.services.BalanceService;
import budget.services.FileService;
import budget.services.IncomeService;
import budget.services.PurchaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
       Scanner scanner =  new Scanner(System.in);
       List<Transaction> transactions = new ArrayList<>();
       showTheMenu();
        while (scanner.hasNext()) {

          int item = scanner.nextInt();

          switch (item) {
            case 1: IncomeService.addIncome(transactions, scanner); break;
            case 2: PurchaseService.addPurchase(transactions, scanner); break;
            case 3: PurchaseService.switchPurchaseCategory(transactions, scanner); break;
            case 4: BalanceService.showBalance(transactions); break;
            case 5: FileService.savePurchasesToFile(transactions); break;
            case 6: FileService.loadTransactions(transactions); break;
            case 7: AnalyzeService.pickAnalyzeMenu(transactions, scanner); break;
            case 0:
              System.out.println();
              System.out.println("Bye!");
              System.exit(200);
              break;
          }
          showTheMenu();
        }
    }

    public static void showTheMenu() {
      System.out.println();
      System.out.println("Choose your action:");
      System.out.println("1) Add income");
      System.out.println("2) Add purchase");
      System.out.println("3) Show list of purchases");
      System.out.println("4) Balance");
      System.out.println("5) Save");
      System.out.println("6) Load");
      System.out.println("7) Analyze (Sort)");
      System.out.println("0) Exit");
    }




}


