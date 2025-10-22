package delivery.service; // Must be the very first line, matching the 'service' folder

// 1. Import the class it implements (OrderService is in the same package, but we'll import Order)
import delivery.model.Order;
import delivery.model.OrderItem; // Needed for the subtotal calculation

public class OrderServiceImpl implements OrderService {

    @Override
    public double calculateSubtotal(Order order) {
        double subtotal = 0.0;

        // **FIXED LOGIC:** Iterate over the values (OrderItem objects) in the items map
        for (OrderItem item : order.getItems().values()) {
            subtotal += item.calculateItemTotal();
        }
        return subtotal;
    }

    @Override
    public double calculateTax(double subtotal) {
        // TAX_RATE comes from the OrderService interface
        return subtotal * TAX_RATE;
    }

    @Override
    public double calculateGrandTotal(double subtotal, double tax) {
        // DELIVERY_FEE comes from the OrderService interface
        return subtotal + tax + DELIVERY_FEE;
    }
}