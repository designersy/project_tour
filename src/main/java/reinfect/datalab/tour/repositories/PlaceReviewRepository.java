package reinfect.datalab.tour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.PlaceReview;

@Repository
public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {
}