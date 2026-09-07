
import java.util.Scanner;

public class UpdateItemFunctions {
    private final Scanner sc;
    private final int QUANTITY_LIMIT = 100000;
    
    public UpdateItemFunctions(Scanner sc) {
        this.sc = sc;
    }

    public boolean updateQuantity(String itemId, Items item) {

        boolean isValid = false;
        boolean isUpdated = false;

        System.out.println("=".repeat(30));

        // CHECK IF ID EXISTS FIRST
        if (item == null) {
            MessagesFunctions.itemIdNotFoundMessage();
            return false;
        }

        int oldQuantity;

        while (!isValid) {
            System.out.printf("Input new Quantity for Item ID: %s (0-100,000): ", itemId);

            String newQuantityTemp = sc.nextLine().trim();

            try {
                int newQuantity = Integer.parseInt(newQuantityTemp);

                System.out.println("=".repeat(30));

                if (newQuantity < 0 || newQuantity > QUANTITY_LIMIT) {
                    MessagesFunctions.errorNumberMessage();
                    continue;
                }

                if (newQuantity == item.getQuantity()) {
                    MessagesFunctions.sameItemPriceQuantityMessage(item.getName(), "quantity");
                    return false;
                }

                oldQuantity = item.getQuantity();
                item.setQuantity(newQuantity);

                MessagesFunctions.updateQuanityMessage(oldQuantity, newQuantity, item.getName());

                isValid = true;
                isUpdated = true;

            } catch (NumberFormatException e) {
                MessagesFunctions.errorNumberMessage();
            }
        }

        return isUpdated;
    }

    public boolean updatePrice(String itemId, Items item) {
        boolean isValid = false;
        boolean isUpdated = false;

        System.out.println("=".repeat(30));

        if (item == null) {
            MessagesFunctions.itemIdNotFoundMessage();
            return false;
        }

        while (!isValid) {
            System.out.printf("Input new Price for Item ID: %s: P", itemId);
            String newPriceTemp = sc.nextLine().trim();
            double oldPrice;

            try {
                double newPrice = Double.parseDouble(newPriceTemp);

                System.out.println("=".repeat(30));

                if (newPrice <= 0 || !Validations.checkValidDouble(newPriceTemp) || Double.isNaN(newPrice) || Double.isInfinite(newPrice)) {
                    MessagesFunctions.errorNumberMessage();
                    continue;
                }

                if(newPrice == item.getPrice()) {
                    MessagesFunctions.sameItemPriceQuantityMessage(item.getName(), "price");
                    return false;
                }

                oldPrice = item.getPrice();
                item.setPrice(newPrice);

                MessagesFunctions.updatePriceMessage(oldPrice, newPrice, item.getName());

                isValid = true;
                isUpdated = true;

            } catch (NumberFormatException e) {
                MessagesFunctions.errorNumberMessage();
            }
        }

        return isUpdated;
    }
}