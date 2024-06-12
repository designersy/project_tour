package reinfect.datalab.tour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.FoodRating;

@Repository
public interface FoodRatingRepository extends JpaRepository<FoodRating, Long> {
}