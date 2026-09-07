public abstract class Items {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Items(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public abstract String getCategory();
}