package delivery.model;

public class MenuItem {
    private final int id;
    public final String name;
    private final double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id; // <-- Ensures getId() returns the correct type
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // This method is often the source of 'Missing return statement' errors
    @Override
    public String toString() {
        // Formats the item name and price for display
        return String.format("%-25s R%.2f", name, price);
    }

    public String name() {
        return "";
    }
}