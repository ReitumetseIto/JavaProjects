package delivery.model;

public class OrderItem {
    private final MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // Calculation method
    public double calculateItemTotal() {
        return menuItem.getPrice();
    }

    // Getters/Setters
    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}