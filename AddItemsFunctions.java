import java.util.ArrayList;
import java.util.Scanner;

public class AddItemsFunctions {
    private final Scanner sc;
    private final ArrayList<Items> items;
    CheckerFunctions checkerFunctions;

    public AddItemsFunctions(Scanner sc, ArrayList<Items> items) {
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

            if (userInput.equalsIgnoreCase("clothing")
                    || userInput.equalsIgnoreCase("electronics")
                    || userInput.equalsIgnoreCase("entertainment")) {
                isValid = true;
            } else {
                GeneralFunctions.errorCategoryMessage();
            }
        }

        if (userInput.equalsIgnoreCase("clothing")) {
            return "CLOTHING";
        } else if (userInput.equalsIgnoreCase("electronics")) {
            return "ELECTRONICS";
        } else if (userInput.equalsIgnoreCase("entertainment")) {
            return "ENTERTAINMENT";
        }

        return null;
    }

    public String getId(String itemCategory) {
        boolean isValid = false;
        String itemId = "";

        while (!isValid) {
            System.out.print("Enter Item's ID (ABC-1234): ");
            String userInputTemp = sc.nextLine().trim();

            if (checkerFunctions.checkItemIdExists(userInputTemp)){
                GeneralFunctions.itemIdExistMessage();
                continue;
            }
            
            if (Validations.isItemValid(userInputTemp)) {
                itemId += userInputTemp;
                isValid = true;
            } else {
                GeneralFunctions.errorStringMessage();
            }
        }

        return itemId;
    }

    public String getName() {
        boolean isValid = false;
        String name = "";

        while (!isValid) {
            System.out.print("Enter item's Name: ");
            name = sc.nextLine().trim();

            if (Validations.isItemNameValid(name)) {
                isValid = true;
            } else {
                GeneralFunctions.errorStringMessage();
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

                if (quantity <= 0) {
                    GeneralFunctions.errorNumberMessage();
                    continue;
                }

                isValid = true;
            } catch (NumberFormatException e) {
                GeneralFunctions.errorNumberMessage();
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
                GeneralFunctions.errorNumberMessage();
                continue;
            }

            try {
                itemPrice = Double.parseDouble(itemPriceTemp);

                if (itemPrice < 0) {
                    GeneralFunctions.errorNumberMessage();
                    continue;
                }

                isValid = true;
            } catch (NumberFormatException e) {
                GeneralFunctions.errorNumberMessage();
            }
        }

        return itemPrice;
    }
}