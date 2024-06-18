package reinfect.datalab.tour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.PlaceRating;

@Repository
public interface PlaceRatingRepository extends JpaRepository<PlaceRating, Long> {
}