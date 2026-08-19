public class Validations {
    private final static String ITEM_ID_REGEX = "^[A-Za-z]{3}-[0-9]{4}$";
    private final static String ITEM_NAME_REGEX = "^[A-Za-z0-9][A-Za-z0-9 .&/'-]*$";
    private final static String DOUBLE_PATTERN = "^[1-9]\\d*(\\.\\d{1,2})?$";

    public static boolean isItemValid(String itemId){
        return itemId.matches(ITEM_ID_REGEX);
    }

    public static boolean isItemNameValid(String itemName){
        return itemName.matches(ITEM_NAME_REGEX);
    }

    public static boolean checkValidDouble(String userInput){
        return userInput.matches(DOUBLE_PATTERN);
    }
}
