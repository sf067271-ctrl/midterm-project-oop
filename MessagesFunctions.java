public class MessagesFunctions {
    
    public static void errorNumberMessage(){
        System.out.println("Invalid input! Please input a valid number.");
    }

    public static void errorStringMessage(){
        System.out.println("Invalid input! Please input a valid format.");
    }

    public static void errorCategoryMessage(){
        System.out.println("Invalid input! Please input (Clothing, Electronics, and Entertainment).");
    }

    public static void addedSuccessMessage(){
        System.out.println("Item added sucessfully!");
    }

    public static void arrayEmptyMessage(){
        System.out.println("Inventory is empty.");
    }

    public static void switchErrorMessage(){
        System.out.println("Invalid choice. Please input a valid choice.");
    }

    public static void itemIdExistMessage(){
        System.out.println("Invalid ID. The ID already exists!");
    }

    public static void itemIdNotFoundMessage(){
        System.out.println("Item not found!");
    }

    public static void updateQuanityMessage(int oldQuantity, int newQuantity, String itemName){
        System.out.printf("Quantity of item %s is updated from %d to %d\n", itemName, oldQuantity, newQuantity);
    }

    public static void updatePriceMessage(double oldPrice, double newPrice, String itemName){
        System.out.printf("Quantity of item %s is updated from P%,.2f to P%,.2f\n", itemName, oldPrice, newPrice);
    }

    public static void removeItemMessage(String itemName){
        System.out.printf("Item %s has been removed from the inventory.\n", itemName);
    }

    public static void missingCategoryMessage(String category){
        System.out.printf("No Category %s found in the inventory!\n", category);
    }
}
