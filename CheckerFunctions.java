import java.util.ArrayList;

public class CheckerFunctions{
    private final ArrayList<Items> items;

    public CheckerFunctions(ArrayList<Items> items){
        this.items = items;
    }

    public boolean checkItemIdExists(String itemId){
        Items item = findItem(itemId);

        if (item != null){
            return true;
        } else {
            return false;
        }
    }

    public Items findItem(String itemId){
        for (Items item : items){
            if (item.getId().equalsIgnoreCase(itemId)){
                return item;
            }
        }

        return null;
    }
}