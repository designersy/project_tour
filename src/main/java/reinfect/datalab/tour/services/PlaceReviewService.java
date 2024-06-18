package reinfect.datalab.tour.services;

import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.entities.PlaceRating;
import reinfect.datalab.tour.entities.PlaceReview;
import reinfect.datalab.tour.enums.LatestType;
import reinfect.datalab.tour.http.forms.PlaceForm;

import java.util.List;
import java.util.Map;

public interface PlaceReviewService {

    void register(String content, int score, Long id) throws Exception;
    void update(String content, Long id) throws Exception;
    void delete(Long id);

    PlaceReview currentItem(Long id) throws Exception;
}
