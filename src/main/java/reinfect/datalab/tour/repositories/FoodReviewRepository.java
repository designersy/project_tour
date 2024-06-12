package reinfect.datalab.tour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.FoodReview;

@Repository
public interface FoodReviewRepository extends JpaRepository<FoodReview, Long> {
}