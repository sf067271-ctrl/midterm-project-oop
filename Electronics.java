public class Electronics extends Items {
    public Electronics(String ID, String name, int quantity, double price){
        super(ID, name, quantity, price);
    }

    @Override
    public String getCategory(){
        return "ELECTRONICS";
    }
}
