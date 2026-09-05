public class MessagesFunctions {

    private static final String LINE = "------------------------------------------------------------";

    public static void errorNumberMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Invalid input! Please input a valid number.");
        System.out.println(LINE);
    }

    public static void errorStringMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Invalid input! Please input a valid format.");
        System.out.println(LINE);
    }

    public static void addedSuccessMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Item added successfully!");
        System.out.println(LINE);
    }

    public static void arrayEmptyMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Inventory is empty.");
        System.out.println(LINE);
    }

    public static void switchErrorMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Invalid choice. Please input a valid choice.");
        System.out.println(LINE);
    }

    public static void itemIdExistMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Invalid ID. The ID already exists!");
        System.out.println(LINE);
    }

    public static void itemIdNotFoundMessage() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Item not found!");
        System.out.println(LINE);
    }

    public static void updateQuanityMessage(int oldQuantity, int newQuantity, String itemName) {
        System.out.println();
        System.out.println(LINE);
        System.out.printf("Quantity of item %s is updated from %d to %d%n", itemName, oldQuantity, newQuantity);
        System.out.println(LINE);
    }

    public static void updatePriceMessage(double oldPrice, double newPrice, String itemName) {
        System.out.println();
        System.out.println(LINE);
        System.out.printf("Price of item %s is updated from P%,.2f to P%,.2f%n", itemName, oldPrice, newPrice);
        System.out.println(LINE);
    }

    public static void removeItemMessage(String itemName) {
        System.out.println();
        System.out.println(LINE);
        System.out.printf("Item %s has been removed from the inventory.%n", itemName);
        System.out.println(LINE);
    }

    public static void errorCategoryMessage(String category) {
        System.out.println();
        System.out.println(LINE);
        System.out.printf("Category %s does not exist!%n", category);
        System.out.println(LINE);
    }
}