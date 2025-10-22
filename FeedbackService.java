package delivery.service;

import delivery.model.Rating;

import java.util.List;

public interface FeedbackService<Rating> {
    void submitRating(Rating rating);

    void submitRating(delivery.model.Rating rating);

    List<Rating> getAllRatings();
    double getAverageRating();
}