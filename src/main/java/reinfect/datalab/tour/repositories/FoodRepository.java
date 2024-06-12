package reinfect.datalab.tour.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.Food;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "SELECT * FROM foods order by RAND() limit 9",nativeQuery = true)
    List<Food> findLatestRandom();

    List<Food> findTop10OrderByOrderByIdDesc();

    Page<Food> findAllByOrderByIdDesc(Pageable pageable);
    Page<Food> findAllByNameContainingOrderByIdDesc(String name, Pageable pageable);
    Page<Food> findAllByNewAddressContainingOrderByIdDesc(String newAddress, Pageable pageable);
    Page<Food> findAllByMainFoodContainingOrderByIdDesc(String mainFood, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredRatings(Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r WHERE e.name LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredRatingsAndSearchName(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r WHERE e.newAddress LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredRatingsAndSearchNewAddress(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r WHERE e.mainFood LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredRatingsAndSearchMainFood(@Param("keyword") String keyword, Pageable pageable);



    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviews(Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r WHERE e.name LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviewsAndSearchName(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r WHERE e.newAddress LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviewsAndSearchNewAddress(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r WHERE e.mainFood LIKE %:keyword% GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviewsAndSearchMainFood(@Param("keyword") String keyword, Pageable pageable);

}