import java.util.ArrayList;
import java.util.Scanner;

public class UpdateItemFunctions {

    private final Scanner sc;
    private final ArrayList<Items> items;
    CheckerFunctions checkerFunctions;

    public UpdateItemFunctions(Scanner sc, ArrayList<Items> items) {
        this.sc = sc;
        this.items = items;
        this.checkerFunctions = new CheckerFunctions(this.items);
    }

    private String getInputId(){
        boolean isValid = false;
        String itemID = "";

        while (!isValid) {
            System.out.print("Input Item's ID: ");
            itemID = sc.nextLine().trim();

            if (!Validations.isItemValid(itemID)){
                GeneralFunctions.errorStringMessage();
                continue;
            }
            
            if (checkerFunctions.checkItemIdExists(itemID)){
                isValid = true;
            } else {
                GeneralFunctions.itemIdNotFoundMessage();
            }
        }

        return itemID;
    }

    public void updateQuantity() {

        boolean isValid = false;
        String itemId = getInputId();

        while(!isValid){
            System.out.printf("Input new Quantity for (Item ID: %s): ", itemId);
            String newQuantityTemp = sc.nextLine();
            int oldQuantity;

            try {
                int newQuantity = Integer.parseInt(newQuantityTemp);
                oldQuantity = newQuantity;

                if (newQuantity < 0){
                    GeneralFunctions.errorNumberMessage();
                    continue;
                }

                Items item = checkerFunctions.findItem(itemId);

                if (item != null){
                    item.setQuantity(newQuantity);
                    GeneralFunctions.updateQuanityMessage(oldQuantity, newQuantity, item.getName());
                    isValid = true;
                } else {
                    GeneralFunctions.itemIdNotFoundMessage();
                }
                
            } catch (NumberFormatException e){
                GeneralFunctions.errorNumberMessage();
            }
        }
    }

    public void updatePrice() {
        boolean isValid = false;
        String itemId = getInputId();

        while(!isValid){
            System.out.printf("Input new Price for (Item ID: %s): P", itemId);
            String newPriceTemp = sc.nextLine();
            double oldPrice;

            try {
                double newPrice = Double.parseDouble(newPriceTemp);
                oldPrice = newPrice;

                if (newPrice <= 0 && !Validations.checkValidDouble(newPriceTemp)){
                    GeneralFunctions.errorNumberMessage();
                    continue;
                }

                Items item = checkerFunctions.findItem(itemId);

                if (item != null){
                    item.setPrice(newPrice);
                    GeneralFunctions.updatePriceMessage(oldPrice, newPrice, item.getName());
                    isValid = true;
                } else {
                    GeneralFunctions.itemIdNotFoundMessage();
                }

            } catch (NumberFormatException e){
                GeneralFunctions.errorNumberMessage();
            }
        }
    }
}