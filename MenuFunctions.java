import java.util.Scanner;
import java.util.ArrayList;

public class MenuFunctions {

    private static final String BORDER = "=".repeat(70);
    private static final String LINE = "-".repeat(70);

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

    private void printHeader(String title) {
        System.out.println(); 
        System.out.println(BORDER);
        System.out.printf("%" + ((70 + title.length()) / 2) + "s%n", title);
        System.out.println(BORDER);
    }

    public void addItem() {

        printHeader("ADD ITEM");

        ItemsInformation addItems = new ItemsInformation(sc, ItemInventory);

        String category = addItems.getCategory();
        String itemId = addItems.getId("addItem").toUpperCase();
        String itemName = addItems.getName();
        int itemQuantity = addItems.getQuantity();
        double itemPrice = addItems.getPrice();

        if (category.equalsIgnoreCase("clothing")) {
            ItemInventory.add(new Clothing(itemId, itemName, itemQuantity, itemPrice));
        } else if (category.equalsIgnoreCase("entertainment")) {
            ItemInventory.add(new Entertainment(itemId, itemName, itemQuantity, itemPrice));
        } else if (category.equalsIgnoreCase("electronics")) {
            ItemInventory.add(new Electronics(itemId, itemName, itemQuantity, itemPrice));
        }

        MessagesFunctions.addedSuccessMessage();
    }

    public void updateItem() {

        printHeader("UPDATE ITEM");

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        boolean isValid = false;
        boolean isUpdated = false;

        while (!isValid) {

            String itemId = information.getId("checker");

            Items item = checkerFunctions.findItem(itemId);

            if (item != null && !isUpdated) {

                System.out.println();
                System.out.println(LINE);
                System.out.println("What option would you like to update?");
                System.out.println("1. Quantity");
                System.out.println("2. Price");
                System.out.println(LINE);
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

        printHeader("REMOVE ITEM");

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String itemId = information.getId("checker");

        Items item = checkerFunctions.findItem(itemId);

        if (item != null) {
            ItemInventory.remove(item);
            MessagesFunctions.removeItemMessage(item.getName());
        }
    }

    public void displayItemsByCategory() {

        printHeader("DISPLAY ITEMS BY CATEGORY");

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String userInput = information.getCategory();

        String idHeader = "ID";
        String nameHeader = "Name";
        String quantityHeader = "Quantity";
        String priceHeader = "Price";

        int idWidth = idHeader.length();
        int nameWidth = nameHeader.length();
        int quantityWidth = quantityHeader.length();
        int priceWidth = priceHeader.length();

        boolean isFound = false;

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

        if (!isFound) {
            MessagesFunctions.errorCategoryMessage(userInput);
            return;
        }

        idWidth += 2;
        nameWidth += 2;
        quantityWidth += 2;
        priceWidth += 2;

        String rowFormat = "%-" + idWidth + "s"
                + "%-" + nameWidth + "s"
                + "%-" + quantityWidth + "s"
                + "%-" + priceWidth + "s%n";

        int totalWidth = idWidth
                + nameWidth
                + quantityWidth
                + priceWidth;

        String separator = "=".repeat(totalWidth);
        String middleSeparator = "-".repeat(totalWidth);

        System.out.println();
        System.out.println(separator);

        String title = "ITEMS UNDER " + userInput.toUpperCase();

        System.out.printf("%" + ((totalWidth + title.length()) / 2) + "s%n", title);

        System.out.println(separator);

        System.out.printf(
                rowFormat,
                idHeader,
                nameHeader,
                quantityHeader,
                priceHeader);

        System.out.println(middleSeparator);

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

        System.out.println(separator);
    }

    public void displayAllItems() {

        if (ItemInventory.isEmpty()) {
            printHeader("DISPLAY ALL ITEMS");
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

        for (Items item : ItemInventory) {

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

        idWidth += 2;
        nameWidth += 2;
        quantityWidth += 2;
        priceWidth += 2;
        categoryWidth += 2;

        String rowFormat = "%-" + idWidth + "s"
                + "%-" + nameWidth + "s"
                + "%-" + quantityWidth + "s"
                + "%-" + priceWidth + "s"
                + "%-" + categoryWidth + "s%n";

        int totalWidth = idWidth
                + nameWidth
                + quantityWidth
                + priceWidth
                + categoryWidth;

        String separator = "=".repeat(totalWidth);
        String middleSeparator = "-".repeat(totalWidth);

        System.out.println();
        System.out.println(separator);

        String title = "DISPLAY ALL ITEMS";

        System.out.printf("%" + ((totalWidth + title.length()) / 2) + "s%n", title);

        System.out.println(separator);

        System.out.printf(
                rowFormat,
                idHeader,
                nameHeader,
                quantityHeader,
                priceHeader,
                categoryHeader);

        System.out.println(middleSeparator);

        for (Items item : ItemInventory) {

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

        System.out.println(separator);
    }

    public void searchItem() {

        printHeader("SEARCH ITEM");

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        String itemID = information.getId("checker");

        Items item = checkerFunctions.findItem(itemID);

        if (item != null) {

            System.out.println();
            System.out.println(LINE);
            System.out.println("Item Found!");
            System.out.println(LINE);

            System.out.printf("%-12s: %s%n", "ID", item.getId());
            System.out.printf("%-12s: %s%n", "Name", item.getName());
            System.out.printf("%-12s: %d%n", "Quantity", item.getQuantity());
            System.out.printf("%-12s: P%,.2f%n", "Price", item.getPrice());

            System.out.printf("%-12s: %s%n", "Category", item.getCategory());

            System.out.println(LINE);
        }
    }

    public void sortItem() {

        printHeader("SORT ITEMS");

        if (ItemInventory.isEmpty()) {
            MessagesFunctions.arrayEmptyMessage();
            return;
        }

        boolean isValid = false;

        while (!isValid) {

            System.out.println();
            System.out.println(LINE);
            System.out.println("What option would you like to sort by?");
            System.out.println("1. Quantity");
            System.out.println("2. Price");
            System.out.println(LINE);
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

                default ->
                    MessagesFunctions.switchErrorMessage();
            }
        }
    }

    public void displayLowStockItems() {

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

        if (!isFound) {
            System.out.println();
            System.out.println(LINE);
            System.out.println("No items are currently low on stock.");
            System.out.println(LINE);

            return;
        }

        idWidth += 2;
        nameWidth += 2;
        quantityWidth += 2;
        priceWidth += 2;
        categoryWidth += 2;

        String rowFormat = "%-" + idWidth + "s"
                + "%-" + nameWidth + "s"
                + "%-" + quantityWidth + "s"
                + "%-" + priceWidth + "s"
                + "%-" + categoryWidth + "s%n";

        int totalWidth = idWidth
                + nameWidth
                + quantityWidth
                + priceWidth
                + categoryWidth;

        String separator = "=".repeat(totalWidth);
        String middleSeparator = "-".repeat(totalWidth);

        System.out.println();
        System.out.println(separator);

        String title = "LOW STOCK ITEMS (QUANTITY <= 5)";

        System.out.printf("%" + ((totalWidth + title.length()) / 2) + "s%n", title);

        System.out.println(separator);

        System.out.printf(
                rowFormat,
                idHeader,
                nameHeader,
                quantityHeader,
                priceHeader,
                categoryHeader);

        System.out.println(middleSeparator);

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
        System.out.println();
        System.out.println(BORDER);
        System.out.printf(
                "%36s%n",
                "Exiting Program...");
        System.out.println(BORDER);

        return true;
    }
}