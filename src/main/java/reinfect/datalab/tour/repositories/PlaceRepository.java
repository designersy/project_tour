package reinfect.datalab.tour.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.Food;
import reinfect.datalab.tour.entities.Place;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "SELECT * FROM places order by RAND() limit 9",nativeQuery = true)
    List<Place> findLatestRandom();

    List<Place> findTop10OrderByOrderByIdDesc();

    Page<Place> findAllByOrderByIdDesc(Pageable pageable);
    Page<Place> findAllByNameContainsOrderByIdDesc(String name, Pageable pageable);
    Page<Place> findAllByNewAddressContainsOrderByIdDesc(String newAddress, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r GROUP BY e.id ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatings(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.name LIKE %:keyword% GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchName(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.newAddress LIKE %:keyword% GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNewAddress(@Param("keyword") String keyword, Pageable pageable);



    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviews(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.name LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchName(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.newAddress LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNewAddress(@Param("keyword") String keyword, Pageable pageable);


}