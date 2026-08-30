import java.util.Scanner;
import java.util.ArrayList;

public class MenuFunctions {
    final private Scanner sc;
    final private ArrayList<Items> ItemInventory = new ArrayList<>();

    UpdateItemFunctions updateItemFunctions;
    CheckerFunctions checkerFunctions;
    SortItemsFunctions sortItemsFunctions;
    ItemsInformation information;

    public MenuFunctions(Scanner sc) {
        this.sc = sc;
        this.updateItemFunctions = new UpdateItemFunctions(this.sc, ItemInventory);
        this.checkerFunctions = new CheckerFunctions(ItemInventory);
        this.sortItemsFunctions = new SortItemsFunctions(this.sc, ItemInventory);
        this.information = new ItemsInformation(this.sc, ItemInventory);
        ItemInventory.add(new Clothing("ABC-1234", "Louie", 2, 23.45));
    }

    public void addItem() {
        System.out.println("=".repeat(30));
        System.out.printf("%18s\n", "ADD ITEM");
        System.out.println("=".repeat(30));

        ItemsInformation addItems = new ItemsInformation(sc, ItemInventory);

        String category = addItems.getCategory();

        String itemName = addItems.getName();
        String itemId = addItems.getId("addItem").toUpperCase();
        int itemQuantity = addItems.getQuantity();
        double itemPrice = addItems.getPrice();

        System.out.println("=".repeat(30));

        if (category.equalsIgnoreCase("clothing")) {
            ItemInventory.add(new Clothing(itemId, itemName, itemQuantity, itemPrice));
            MessagesFunctions.addedSuccessMessage();
        } else if (category.equalsIgnoreCase("entertainment")) {
            ItemInventory.add(new Entertainment(itemId, itemName, itemQuantity, itemPrice));
            MessagesFunctions.addedSuccessMessage();
        } else if (category.equalsIgnoreCase("electronics")) {
            ItemInventory.add(new Electronics(itemId, itemName, itemQuantity, itemPrice));
            MessagesFunctions.addedSuccessMessage();
        }
    }

    public void updateItem() {
        boolean isValid = false;
        boolean isUpdated = false;

        System.out.println("=".repeat(30));
        System.out.printf("%20s\n", "UPDATE ITEM");
        System.out.println("=".repeat(30));

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        while(!isValid){
            // Asks for the item's id before updating an item
            String itemId = information.getId("checker");

            Items item = checkerFunctions.findItem(itemId);

            while (item != null && !isUpdated){
                System.out.println("What option would you like to update: ");
                System.out.println("1. Quantity");
                System.out.println("2. Price");
                System.out.print("Enter your choice: ");
                
                String userChoice = sc.nextLine().trim();

                switch (userChoice) {
                    case "1" -> {
                        isUpdated = updateItemFunctions.updateQuantity(itemId, item);
                        isValid = true;
                    }
                    case "2" -> {
                        isUpdated = updateItemFunctions.updatePrice(itemId, item);
                        isValid = true;
                    }
                    default -> MessagesFunctions.switchErrorMessage();
                }
            }
        }
    }

    public void removeItem() {

        boolean isValid = false;

        if (ItemInventory.isEmpty()) {
            System.out.println("=".repeat(30));
            MessagesFunctions.arrayEmptyMessage();
            return;            
        }

        while (!isValid) {
            System.out.println("=".repeat(30));
            String itemId = information.getId("checker");

            Items item = checkerFunctions.findItem(itemId);

            System.out.println("=".repeat(30));

            if (item != null) {
                ItemInventory.remove(item);
                MessagesFunctions.removeItemMessage(item.getName());
                isValid = true;
            }
        }
    }

    public void displayItemsByCategory() {
        System.out.println("=".repeat(30));

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String userInput = information.getCategory();

        // Column headers
        String idHeader = "ID";
        String nameHeader = "Name";
        String quantityHeader = "Quantity";
        String priceHeader = "Price";

        // Start column widths based on header lengths
        int idWidth = idHeader.length();
        int nameWidth = nameHeader.length();
        int quantityWidth = quantityHeader.length();
        int priceWidth = priceHeader.length();

        boolean isFound = false;

        // Find the longest value in each column
        for (Items item : ItemInventory) {
            if (item.getCategory().equalsIgnoreCase(userInput)) {

                isFound = true;

                String id = item.getId();
                String name = item.getName();
                String quantity = String.valueOf(item.getQuantity());
                String price = String.format("P%,.2f", item.getPrice());

                idWidth = Math.max(idWidth, id.length());
                nameWidth = Math.max(nameWidth, name.length());
                quantityWidth = Math.max(quantityWidth, quantity.length());
                priceWidth = Math.max(priceWidth, price.length());
            }
        }

        System.out.println("=".repeat(30));

        if (!isFound) {
            MessagesFunctions.errorCategoryMessage(userInput); // userInput is a category
            return;
        }

        // Add padding between columns
        idWidth += 2;
        nameWidth += 2;
        quantityWidth += 2;
        priceWidth += 2;

        // Create dynamic row format
        String rowFormat = "%-" + idWidth + "s"
                + "%-" + nameWidth + "s"
                + "%-" + quantityWidth + "s"
                + "%-" + priceWidth + "s";

        // Calculate total table width
        int totalWidth = idWidth
                + nameWidth
                + quantityWidth
                + priceWidth;

        // Separator
        String separator = "=".repeat(totalWidth);

        // Display table
        System.out.println("\n\n" + separator);
        System.out.println("Items under " + userInput.toUpperCase() + ":");
        System.out.println(separator);

        System.out.printf(
                rowFormat,
                idHeader,
                nameHeader,
                quantityHeader,
                priceHeader);

        System.out.println("\n" + separator);

        // Display matching items
        for (Items item : ItemInventory) {
            if (item.getCategory().equalsIgnoreCase(userInput)) {

                String id = item.getId();
                String name = item.getName();
                String quantity = String.valueOf(item.getQuantity());
                String price = String.format("P%,.2f", item.getPrice());

                System.out.printf(
                        rowFormat,
                        id,
                        name,
                        quantity,
                        price);
            }
        }

        System.out.println("\n" + separator + "\n\n");
    }

    public void displayAllItems() {
        if (ItemInventory.isEmpty()) {
            System.out.println("=".repeat(30));
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String idHeader = "ID";
        String nameHeader = "Name";
        String quantityHeader = "Quantity";
        String priceHeader = "Price";
        String categoryHeader = "Category";

        int idWidth = idHeader.length();
        int nameWidth = nameHeader.length();
        int quantityWidth = quantityHeader.length();
        int priceWidth = priceHeader.length();
        int categoryWidth = categoryHeader.length();

        // Find the longest value in each column
        for (Items item : ItemInventory) {

            String id = item.getId();
            String name = item.getName();
            String quantity = String.valueOf(item.getQuantity());
            String price = String.format("%.2f", item.getPrice());
            String category = item.getCategory();

            idWidth = Math.max(idWidth, id.length());
            nameWidth = Math.max(nameWidth, name.length());
            quantityWidth = Math.max(quantityWidth, quantity.length());
            priceWidth = Math.max(priceWidth, price.length());
            categoryWidth = Math.max(categoryWidth, category.length());
        }

        // Add some spacing between columns
        idWidth += 2;
        nameWidth += 2;
        quantityWidth += 2;
        priceWidth += 4;
        categoryWidth += 2;

        // Get the total width of the table
        int totalWidth = idWidth + nameWidth + quantityWidth + priceWidth + categoryWidth;

        System.out.println("=".repeat(totalWidth));
        System.out.printf("%32s%n", "DISPLAY ALL ITEMS");
        System.out.println("=".repeat(totalWidth));

        // Print header
        System.out.printf("%-" + idWidth + "s", idHeader);
        System.out.printf("%-" + nameWidth + "s", nameHeader);
        System.out.printf("%-" + quantityWidth + "s", quantityHeader);
        System.out.printf("%-" + priceWidth + "s", priceHeader);
        System.out.printf("%-" + categoryWidth + "s%n", categoryHeader);

        System.out.println("=".repeat(totalWidth));

        // Print items
        for (Items item : ItemInventory) {

            String category = item.getCategory();
            String id = item.getId();
            String name = item.getName();
            String quantity = String.valueOf(item.getQuantity());
            String price = String.format("P%,.2f", item.getPrice());

            System.out.printf("%-" + idWidth + "s", id);
            System.out.printf("%-" + nameWidth + "s", name);
            System.out.printf("%-" + quantityWidth + "s", quantity);
            System.out.printf("%-" + priceWidth + "s", price);
            System.out.printf("%-" + categoryWidth + "s%n", category);
        }

        System.out.println("=".repeat(totalWidth) + "\n");
    }

    public void searchItem() {
        boolean isFound = false;

        System.out.println("=".repeat(30));

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String itemID = information.getId("checker");

        System.out.println("=".repeat(30));

        while (!isFound) {
            Items item = checkerFunctions.findItem(itemID);

            if (item != null) {
                isFound = true;

                System.out.println("\n==============================================");
                System.out.println("Item Found!");
                System.out.println("==============================================");
                System.out.println("ID       : " + item.getId());
                System.out.println("Name     : " + item.getName());
                System.out.println("Quantity : " + item.getQuantity());
                System.out.printf("Price    : P%,.2f%n", item.getPrice());
                System.out.println("Category : " + item.getCategory());
            } else {
                MessagesFunctions.itemIdNotFoundMessage();
            }
        }
    }

    public void sortItem() {
        boolean isValid = false;

        System.out.println("=".repeat(30));

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        while (!isValid) {
            System.out.println("What option would you like to sort: ");
            System.out.println("1. Quantity");
            System.out.println("2. Price");
            System.out.print("Enter your choice: ");

            String userChoice = sc.nextLine().trim();

            switch (userChoice) {
                case "1" -> {
                    sortItemsFunctions.sortByQuantity();
                    isValid = true;
                }
                case "2" -> {
                    sortItemsFunctions.sortByPrice();
                    isValid = true;
                }
                default -> MessagesFunctions.switchErrorMessage();
            }
        }
    }

    public void displayLowStockItems() {

        System.out.println("=".repeat(30));

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String idHeader = "ID";
        String nameHeader = "Name";
        String quantityHeader = "Quantity";
        String priceHeader = "Price";
        String categoryHeader = "Category";

        int idWidth = idHeader.length();
        int nameWidth = nameHeader.length();
        int quantityWidth = quantityHeader.length();
        int priceWidth = priceHeader.length();
        int categoryWidth = categoryHeader.length();

        boolean isFound = false;

        // Find the longest values of low-stock items
        for (Items item : ItemInventory) {

            if (item.getQuantity() <= 5) {

                isFound = true;

                String id = item.getId();
                String name = item.getName();
                String quantity = String.valueOf(item.getQuantity());
                String price = String.format("P%,.2f", item.getPrice());
                String category = item.getCategory();

                idWidth = Math.max(idWidth, id.length());
                nameWidth = Math.max(nameWidth, name.length());
                quantityWidth = Math.max(quantityWidth, quantity.length());
                priceWidth = Math.max(priceWidth, price.length());
                categoryWidth = Math.max(categoryWidth, category.length());
            }
        }

        // No low-stock items
        if (!isFound) {
            System.out.println("No items are currently low on stock.");
            return;
        }

        // Add spacing
        idWidth += 2;
        nameWidth += 2;
        quantityWidth += 2;
        priceWidth += 2;
        categoryWidth += 2;

        // Create dynamic row format
        String rowFormat = "%-" + idWidth + "s"
                + "%-" + nameWidth + "s"
                + "%-" + quantityWidth + "s"
                + "%-" + priceWidth + "s"
                + "%-" + categoryWidth + "s%n";

        // Calculate table width
        int totalWidth = idWidth
                + nameWidth
                + quantityWidth
                + priceWidth
                + categoryWidth;

        String separator = "=".repeat(totalWidth);

        // Display table
        System.out.println("\nLow Stock Items:");
        System.out.println(separator);

        System.out.printf(
                rowFormat,
                idHeader,
                nameHeader,
                quantityHeader,
                priceHeader,
                categoryHeader);

        System.out.println(separator);

        // Display low-stock items
        for (Items item : ItemInventory) {

            if (item.getQuantity() <= 5) {

                String id = item.getId();
                String name = item.getName();
                String quantity = String.valueOf(item.getQuantity());
                String price = String.format("P%,.2f", item.getPrice());
                String category = item.getCategory();

                System.out.printf(
                        rowFormat,
                        id,
                        name,
                        quantity,
                        price,
                        category);
            }
        }

        System.out.println(separator);
    }

    public boolean exit() {
        System.out.println("=".repeat(30));
        System.out.println("Exiting Program.");
        System.out.println("=".repeat(30));
        return true;
    }
}