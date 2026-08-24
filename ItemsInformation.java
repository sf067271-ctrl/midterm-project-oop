import java.util.ArrayList;
import java.util.Scanner;

public class ItemsInformation {
    private final Scanner sc;
    private final ArrayList<Items> items;
    private final int QUANTITY_LIMIT = 100000;
    CheckerFunctions checkerFunctions;

    public ItemsInformation(Scanner sc, ArrayList<Items> items) {
        this.sc = sc;
        this.items = items;
        this.checkerFunctions = new CheckerFunctions(this.items);
    }

    public String getCategory() {
        boolean isValid = false;
        String userInput = "";

        while (!isValid) {
            System.out.print("Enter Category (Clothing, Electronics, Entertainment): ");
            userInput = sc.nextLine().trim();

            if (userInput.toUpperCase().equals("CLOTHING")
                    || userInput.toUpperCase().equals("ELECTRONICS")
                    || userInput.toUpperCase().equals("ENTERTAINMENT")) {
                isValid = true;
            } else {
                MessagesFunctions.errorCategoryMessage();
            }
        }

        return userInput;
    }

    public String getId(String getIdType) {
        boolean isValid = false;
        String itemId = "";

        while (!isValid) {
            System.out.print("Enter Item's ID (ABC-1234): ");
            String userInputTemp = sc.nextLine().trim();

            if (checkerFunctions.checkItemIdExists(userInputTemp) && "addItem".equalsIgnoreCase(getIdType)){
                MessagesFunctions.itemIdExistMessage();
                continue;
            }
            
            if (Validations.isItemValid(userInputTemp)) {
                itemId = userInputTemp;
                isValid = true;
            } else {
                MessagesFunctions.errorStringMessage();
                continue;
            }

            if ("checker".equalsIgnoreCase(getIdType) && checkerFunctions.checkItemIdExists(itemId)){
                isValid = true;
            } else if ("checker".equalsIgnoreCase(getIdType)) {
                MessagesFunctions.itemIdNotFoundMessage();
            }
        }

        return itemId.toUpperCase();
    }

    public String getName() {
        boolean isValid = false;
        String name = "";

        while (!isValid) {
            System.out.print("Enter Item's Name: ");
            name = sc.nextLine().trim();

            if (Validations.isItemNameValid(name)) {
                isValid = true;
            } else {
                MessagesFunctions.errorStringMessage();
            }
        }

        return name;
    }

    public int getQuantity() {
        boolean isValid = false;
        int quantity = 0;

        while (!isValid) {
            System.out.print("Enter item's Quantity: ");
            String quantityString = sc.nextLine().trim();

            try {
                quantity = Integer.parseInt(quantityString);

                if (quantity <= 0 && quantity > QUANTITY_LIMIT) {
                    MessagesFunctions.errorNumberMessage();
                    continue;
                }

                isValid = true;
            } catch (NumberFormatException e) {
                MessagesFunctions.errorNumberMessage();
            }
        }

        return quantity;
    }

    public double getPrice() {
        boolean isValid = false;
        double itemPrice = 0.0;

        while (!isValid) {
            System.out.print("Enter item's Price: P");
            String itemPriceTemp = sc.nextLine().trim();

            if (!Validations.checkValidDouble(itemPriceTemp)) {
                MessagesFunctions.errorNumberMessage();
                continue;
            }

            try {
                itemPrice = Double.parseDouble(itemPriceTemp);

                if (itemPrice < 0) {
                    MessagesFunctions.errorNumberMessage();
                    continue;
                }

                isValid = true;
            } catch (NumberFormatException e) {
                MessagesFunctions.errorNumberMessage();
            }
        }

        return itemPrice;
    }
}