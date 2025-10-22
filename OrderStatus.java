package delivery;

public enum OrderStatus {
    RECEIVED("Order received and pending confirmation."),
    PREPARING("Kitchen is busy preparing your delicious food."),
    OUT_FOR_DELIVERY("Your food is out for delivery! Expect it soon."),
    DELIVERED("Order successfully delivered. Enjoy your meal!"),
    CANCELLED("Order has been cancelled.");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}