// FINAL COMPLETE FoodDeliveryApp.java SCRIPT
package delivery;

// Project-Specific Imports
import delivery.model.MenuItem;
import delivery.model.Order;
import delivery.model.OrderItem;
import delivery.model.Rating;
import delivery.service.OrderService;
import delivery.service.OrderServiceImpl;
import delivery.service.FeedbackService;
import delivery.service.FeedbackServiceImpl;

// Standard Java Utility Imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FoodDeliveryApp {

    private final OrderService orderService = new OrderServiceImpl();
    private final FeedbackService feedbackService = new FeedbackServiceImpl() {
        @Override
        public void submitRating(Object o) {

        }
    }; // New Service
    private final List<MenuItem> menu = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Order currentOrder = new Order();

    public static void main(String[] args) {
        FoodDeliveryApp app = new FoodDeliveryApp();
        app.setupMenu();
        app.startApplication();
    }

    private void setupMenu() {
        menu.add(new MenuItem(101, "Signature Beef Burger", 89.90));
        menu.add(new MenuItem(102, "Gourmet Halloumi Salad", 75.00));
        menu.add(new MenuItem(103, "Truffle Fries (Large)", 45.50));
        menu.add(new MenuItem(104, "Spicy Chicken Wings (6pc)", 68.00));
    }

    public void startApplication() {
        System.out.println("🍔 Welcome to The Advanced Food Delivery Service 🛵");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Browse Menu");
            System.out.println("2. Place Order (Add Items)");
            System.out.println("3. View Order Summary & Cost");
            System.out.println("4. **CHECKOUT & TRACK ORDER**");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: browseMenu(); break;
                    case 2: placeOrder(); break;
                    case 3: displayOrderSummary(); break;
                    case 4:
                        if (currentOrder.hasItems()) {
                            checkoutAndTrack();
                            running = false;
                        } else {
                            System.out.println("\n❗ Order is empty. Please add items before checking out.");
                        }
                        break;
                    case 5:
                        System.out.println("👋 Thank you for using our advanced service. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void browseMenu() {
        System.out.println("\n--- GOURMET MENU ITEMS ---");
        System.out.println(String.format("%-5s %-25s %s", "ID", "Item Name", "Price"));
        System.out.println("----------------------------------------");
        for (MenuItem item : menu) {
            System.out.println(String.format("%-5d %s", item.getId(), item));
        }
        System.out.println("----------------------------------------");
    }

    private void placeOrder() {
        browseMenu();
        try {
            System.out.print("Enter the **ID** of the item to order (e.g., 101): ");
            int itemId = scanner.nextInt();

            MenuItem selectedItem = menu.stream()
                    .filter(item -> item.getId() == itemId)
                    .findFirst()
                    .orElse(null);

            if (selectedItem != null) {
                System.out.print("Enter quantity for " + selectedItem.getName() + ": ");
                int quantity = scanner.nextInt();
                currentOrder.addItem(selectedItem, quantity);
            } else {
                System.out.println("Invalid item ID. Item not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for ID and quantity.");
            scanner.nextLine();
        }
    }

    private void displayOrderSummary() {
        if (!currentOrder.hasItems()) {
            System.out.println("\nYour order is currently empty.");
            return;
        }

        double subtotal = orderService.calculateSubtotal(currentOrder);
        double tax = orderService.calculateTax(subtotal);
        double grandTotal = orderService.calculateGrandTotal(subtotal, tax);

        System.out.println("\n===================================");
        System.out.println("       ADVANCED ORDER BILL");
        System.out.println("===================================");

        System.out.println("Items Ordered:");
        System.out.println(String.format("%-5s %-20s %s", "Qty", "Item", "Total Price"));
        for (OrderItem orderItem : currentOrder.getItems().values()) {
            MenuItem item = orderItem.getMenuItem();
            System.out.println(String.format("%-5d %-20s R%.2f",
                    orderItem.getQuantity(), item.getName(), orderItem.calculateItemTotal()));
        }

        System.out.println("-----------------------------------");
        System.out.println(String.format("%-25s R%.2f", "Subtotal:", subtotal));
        System.out.println(String.format("%-25s R%.2f", "Tax (" + (int)(OrderService.TAX_RATE * 100) + "%):", tax));
        System.out.println(String.format("%-25s R%.2f", "Delivery Fee:", OrderService.DELIVERY_FEE));
        System.out.println("-----------------------------------");
        System.out.println(String.format("%-25s R%.2f", "**GRAND TOTAL DUE:**", grandTotal));
        System.out.println("===================================");

        System.out.println("Current Status: " + currentOrder.getStatus().name() +
                " (" + currentOrder.getStatus().getDescription() + ")");
    }

    private void checkoutAndTrack() {
        System.out.println("\n💳 Finalizing Order and Payment...");
        displayOrderSummary();

        System.out.println("\n--- Real-Time Order Tracking ---");

        System.out.println("STATUS 1: " + currentOrder.getStatus().name());

        simulateDelay(2000);
        currentOrder.updateStatus(OrderStatus.PREPARING);

        simulateDelay(3000);
        currentOrder.updateStatus(OrderStatus.OUT_FOR_DELIVERY);

        simulateDelay(4000);
        currentOrder.updateStatus(OrderStatus.DELIVERED);

        System.out.println("\n🎉 Congratulations! Your Advanced Food Delivery Order is Complete!");
        promptForRating();
    }

    private void promptForRating() {
        System.out.println("\n-- Customer Feedback --");
        try {
            System.out.print("Please rate your experience (1=Poor to 5=Excellent): ");
            int rating = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Any comments? (Optional, press Enter to skip): ");
            String comments = scanner.nextLine();

            int orderId = currentOrder.hashCode();

            Rating newRating = new Rating(orderId, rating, comments.isEmpty() ? "No comments." : comments);
            feedbackService.submitRating(newRating);

            System.out.printf("Average customer rating is now: %.1f/5\n", feedbackService.getAverageRating());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Rating must be a number.");
            scanner.nextLine();
        }
    }

    private void simulateDelay(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}