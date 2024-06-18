package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.entities.PlaceRating;
import reinfect.datalab.tour.entities.PlaceReview;
import reinfect.datalab.tour.http.forms.PlaceForm;
import reinfect.datalab.tour.repositories.PlaceRatingRepository;
import reinfect.datalab.tour.repositories.PlaceReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IPlaceReviewService implements PlaceReviewService {

    private final PlaceService service;
    private final PlaceReviewRepository reviewRepository;
    private final PlaceRatingRepository ratingRepository;

    @Override
    public void register(String content, int score, Long id) throws Exception {
        Place place = service.currentItem(id);

        PlaceReview review = new PlaceReview();
        review.setContent(content);
        review.setPlace(place);
        List<PlaceReview> reviews = place.getPlaceReviews();
        reviews.add(review);
        place.setPlaceReviews(reviews);
        reviewRepository.save(review);

        PlaceRating rating = new PlaceRating();
        rating.setScore(score);
        rating.setPlace(place);
        List<PlaceRating> ratings = place.getPlaceRatings();
        ratings.add(rating);
        place.setPlaceRatings(ratings);
        ratingRepository.save(rating);
    }

    @Override
    public void update(String content, Long id) throws Exception {
        PlaceReview review = currentItem(id);
        review.setContent(content);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
        ratingRepository.deleteById(id);
    }

    @Override
    public PlaceReview currentItem(Long id) throws Exception {
        return reviewRepository.findById(id).orElseThrow(() -> new Exception("데이터가 없습니다."));
    }
}
