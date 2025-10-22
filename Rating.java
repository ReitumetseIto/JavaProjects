package delivery.model;

/**
 * @param rating 1 (Bad) to 5 (Excellent)
 */
public record Rating(int orderId, int rating, String comments) {
    public Rating(int orderId, int rating, String comments) {
        this.orderId = orderId;
        // Simple validation to cap rating between 1 and 5
        this.rating = Math.min(5, Math.max(1, rating));
        this.comments = comments;
    }

    @Override
    public String toString() {
        return String.format("Order %d - Rating: %d/5 - Comments: \"%s\"",
                orderId, rating, comments);
    }

    public int getRating() {
        return 0;
    }

    public String getOrderId() {
        return "";
    }
}