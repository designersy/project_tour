package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.*;
import reinfect.datalab.tour.repositories.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IFoodReviewService implements FoodReviewService {

    private final FoodService service;
    private final FoodReviewRepository reviewRepository;
    private final FoodRatingRepository ratingRepository;

    @Override
    public void register(String content, int score, Long id) throws Exception {
        Food food = service.currentItem(id);

        FoodReview review = new FoodReview();
        review.setContent(content);
        review.setPlace(food);
        List<FoodReview> reviews = food.getFoodReviews();
        reviews.add(review);
        food.setFoodReviews(reviews);
        reviewRepository.save(review);

        FoodRating rating = new FoodRating();
        rating.setScore(score);
        rating.setFood(food);
        List<FoodRating> ratings = food.getFoodRatings();
        ratings.add(rating);
        food.setFoodRatings(ratings);
        ratingRepository.save(rating);
    }

    @Override
    public void update(String content, Long id) throws Exception {
        FoodReview review = currentItem(id);
        review.setContent(content);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
        ratingRepository.deleteById(id);
    }

    @Override
    public FoodReview currentItem(Long id) throws Exception {
        return reviewRepository.findById(id).orElseThrow(() -> new Exception("데이터가 없습니다."));
    }
}
