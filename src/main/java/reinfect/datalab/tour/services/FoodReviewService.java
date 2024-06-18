package reinfect.datalab.tour.services;

import reinfect.datalab.tour.entities.FoodReview;

public interface FoodReviewService {

    void register(String content, int score, Long id) throws Exception;
    void update(String content, Long id) throws Exception;
    void delete(Long id);

    FoodReview currentItem(Long id) throws Exception;
}
