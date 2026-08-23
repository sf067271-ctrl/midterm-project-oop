import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        boolean exit = false;

        MenuFunctions mf = new MenuFunctions(scanner);

        while (!exit) {
            System.out.println("=".repeat(30));
            System.out.printf("%17s\n", "MENU");
            System.out.println("=".repeat(30));
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Display Items by Category");
            System.out.println("5. Display All Items");
            System.out.println("6. Search Item");
            System.out.println("7. Sort Items");
            System.out.println("8. Display Low Stock Items");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1" -> mf.addItem();
                case "2" -> mf.updateItem();
                case "3" -> mf.removeItem();
                case "4" -> mf.displayItemsByCategory();
                case "5" -> mf.displayAllItems();
                case "6" -> mf.searchItem();
                case "7" -> mf.sortItem();
                case "8" -> mf.displayLowStockItems();
                case "9" -> exit = mf.exit();
                default -> MessagesFunctions.switchErrorMessage();
            }
        }

    }
}
