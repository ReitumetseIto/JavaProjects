package delivery.model;

import delivery.OrderStatus;

import java.util.HashMap;
import java.util.Map;

public class Order {
    // Map of MenuItem to OrderItem for quick lookup and unique key
    private final Map<MenuItem, OrderItem> items = new HashMap<>();
    private OrderStatus status = OrderStatus.RECEIVED; // Use Enum

    // Add or update an item in the order
    public void addItem(MenuItem item, int quantity) {
        if (quantity <= 0) return;

        OrderItem orderItem = items.get(item);
        if (orderItem == null) {
            // New item
            items.put(item, new OrderItem(item, quantity));
        } else {
            // Existing item: update quantity
            orderItem.setQuantity(orderItem.getQuantity() + quantity);
        }
        System.out.println("✅ Added " + quantity + " x " + item.name());
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        // Advanced feature: Email simulation
        System.out.println("\n[SYSTEM NOTIFICATION - EMAIL SENT]");
        System.out.println("To: customer@example.com");
        System.out.println("Subject: Your order is now " + newStatus.name());
        System.out.println("Body: " + newStatus.getDescription());
        System.out.println("-----------------------------------");
    }

    // Getters
    public Map<MenuItem, OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public boolean hasItems() { return !items.isEmpty(); }
}