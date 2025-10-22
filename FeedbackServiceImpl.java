package delivery.service;

import delivery.model.Rating; // <--- REQUIRED IMPORT

import java.util.ArrayList;
import java.util.List;

public abstract class FeedbackServiceImpl implements FeedbackService {
    // In-memory list to store all submitted ratings
    private final List<Rating> ratingsStore = new ArrayList<>();

    @Override
    public void submitRating(Rating rating) {
        ratingsStore.add(rating);
        // Uses the getter method getOrderId()
        System.out.println("\n⭐ Feedback submitted successfully for Order " + rating.getOrderId() + ".");
    }

    @Override
    public List<Rating> getAllRatings() {
        return new ArrayList<>(ratingsStore);
    }

    @Override
    public double getAverageRating() {
        if (ratingsStore.isEmpty()) return 0.0;

        // Correctly references the getter method getRating()
        double totalScore = ratingsStore.stream()
                .mapToInt(Rating::getRating)
                .sum();

        return totalScore / ratingsStore.size();
    }
}