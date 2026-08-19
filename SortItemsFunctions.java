import java.util.ArrayList;
import java.util.Scanner;

public class SortItemsFunctions {

    private final Scanner sc;
    private final ArrayList<Items> items;

    public SortItemsFunctions(Scanner sc, ArrayList<Items> items) {
        this.sc = sc;
        this.items = items;
    }

    public boolean ascendingDescending() {
        // TRUE = ASC
        // FALSE = DESC

        boolean isValid = false;
        boolean ascending = false;

        while (!isValid) {
            System.out.print("Please input if ascending (ASC) or descending (DESC): ");
            String userInput = sc.nextLine().trim();

            if ("ascending".equalsIgnoreCase(userInput)
                    || "asc".equalsIgnoreCase(userInput)) {

                ascending = true;
                isValid = true;

            } else if ("descending".equalsIgnoreCase(userInput)
                    || "desc".equalsIgnoreCase(userInput)) {

                ascending = false;
                isValid = true;

            } else {
                System.out.println("Invalid input. Please enter ASC or DESC.");
            }
        }

        return ascending;
    }

    private void swap(ArrayList<Items> list, int first, int second) {
        Items temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    public void sortByQuantity() {

        boolean ascending = ascendingDescending();

        // This creates a temporary ArrayList that will be sorted instead of the original
        ArrayList<Items> sortedItems = new ArrayList<>(items);

        // Sorts the temporary ArrayList
        for (int i = 0; i < sortedItems.size() - 1; i++) {
            for (int j = i + 1; j < sortedItems.size(); j++) {

                if (ascending && sortedItems.get(i).getQuantity() > sortedItems.get(j).getQuantity()) {
                    swap(sortedItems, i, j);
                }

                if (!ascending && sortedItems.get(i).getQuantity() < sortedItems.get(j).getQuantity()) {
                    swap(sortedItems, i, j);
                }
            }
        }

        // Display the sorted copy
        displayItems(sortedItems);
    }

    public void sortByPrice() {

        boolean ascending = ascendingDescending();

        // This creates a temporary ArrayList that will be sorted instead of the original
        ArrayList<Items> sortedItems = new ArrayList<>(items);

        // Sorts the temporary ArrayList
        for (int i = 0; i < sortedItems.size() - 1; i++) {
            for (int j = i + 1; j < sortedItems.size(); j++) {

                if (ascending
                        && sortedItems.get(i).getPrice() > sortedItems.get(j).getPrice()) {

                    swap(sortedItems, i, j);
                }

                if (!ascending
                        && sortedItems.get(i).getPrice() < sortedItems.get(j).getPrice()) {

                    swap(sortedItems, i, j);
                }
            }
        }

        // Display the sorted copy
        displayItems(sortedItems);
    }

    public void displayItems(ArrayList<Items> itemsToDisplay) {

        if (itemsToDisplay.isEmpty()) {
            System.out.println("No items available.");
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

        // Find longest value in each column
        for (Items item : itemsToDisplay) {

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

        // Add spacing
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

        // Display table
        System.out.println(separator);

        System.out.printf(
                rowFormat,
                idHeader,
                nameHeader,
                quantityHeader,
                priceHeader,
                categoryHeader
        );

        System.out.println(separator);

        for (Items item : itemsToDisplay) {

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
                    category
            );
        }

        System.out.println(separator);
    }
}