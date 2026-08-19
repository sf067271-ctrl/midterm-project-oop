public class Entertainment extends Items{
    public Entertainment(String ID, String name, int quantity, double price){
        super(ID, name, quantity, price);
    }

    @Override
    public String getCategory(){
        return "CLOTHING";
    }
}
