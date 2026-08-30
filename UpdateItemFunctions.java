import java.util.ArrayList;
import java.util.Scanner;

public class UpdateItemFunctions {

    private final Scanner sc;
    private final ArrayList<Items> items;
    CheckerFunctions checkerFunctions;
    ItemsInformation information;

    public UpdateItemFunctions(Scanner sc, ArrayList<Items> items) {
        this.sc = sc;
        this.items = items;
        this.checkerFunctions = new CheckerFunctions(this.items);
        this.information = new ItemsInformation(sc, this.items);
    }

    public boolean updateQuantity(String itemId, Items item) {

        boolean isValid = false;
        boolean isUpdated = false;

        System.out.println("=".repeat(30));

        int oldQuantity;

        while(!isValid){
            System.out.printf("Input new Quantity for (Item ID: %s): ", itemId);
            String newQuantityTemp = sc.nextLine().trim();

            try {
                int newQuantity = Integer.parseInt(newQuantityTemp);

                System.out.println("=".repeat(30));

                if (newQuantity < 0){
                    MessagesFunctions.errorNumberMessage();
                    continue;
                }

                if (item != null){
                    oldQuantity = item.getQuantity();
                    item.setQuantity(newQuantity);
                    MessagesFunctions.updateQuanityMessage(oldQuantity, newQuantity, item.getName());
                    isValid = true;
                    isUpdated = true;
                } else {
                    MessagesFunctions.itemIdNotFoundMessage();
                }
                
            } catch (NumberFormatException e){
                MessagesFunctions.errorNumberMessage();
            }
        }

        return isUpdated;
    }

    public boolean updatePrice(String itemId, Items item) {
        boolean isValid = false;
        boolean isUpdated = false;

        System.out.println("=".repeat(30));

        while(!isValid){
            System.out.printf("Input new Price for (Item ID: %s): P", itemId);
            String newPriceTemp = sc.nextLine();
            double oldPrice;

            try {
                double newPrice = Double.parseDouble(newPriceTemp);

                System.out.println("=".repeat(30));

                if (newPrice <= 0 || !Validations.checkValidDouble(newPriceTemp)){
                    MessagesFunctions.errorNumberMessage();
                    continue;
                }

                if (item != null){
                    oldPrice = item.getPrice();
                    item.setPrice(newPrice);
                    MessagesFunctions.updatePriceMessage(oldPrice, newPrice, item.getName());
                    isValid = true;
                    isUpdated = true;
                } else {
                    MessagesFunctions.itemIdNotFoundMessage();
                }

            } catch (NumberFormatException e){
                MessagesFunctions.errorNumberMessage();
            }
        }

        return isUpdated;
    }
}