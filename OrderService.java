package delivery.service;

// 🚨 Add this line to tell the interface where the 'Order' class lives
import delivery.model.Order;

public interface OrderService {
    double TAX_RATE = 0.15; // 15%
    double DELIVERY_FEE = 25.00; // Flat R25.00

    double calculateSubtotal(Order order);
    double calculateTax(double subtotal);
    double calculateGrandTotal(double subtotal, double tax);
}