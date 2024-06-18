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

    @Query("SELECT e FROM Food e where e.language = :language order by RAND() limit 9")
    List<Food> findLatestRandom(@Param("language") String language);

    List<Food> findTop10OrderByOrderByIdDesc();

    Page<Food> findAllByLanguageOrderByIdDesc(String language, Pageable pageable);
    Page<Food> findAllByLanguageAndNameContainingOrderByIdDesc(String language, String name, Pageable pageable);
    Page<Food> findAllByLanguageAndNewAddressContainingOrderByIdDesc(String language, String newAddress, Pageable pageable);
    Page<Food> findAllByLanguageAndMainFoodContainingOrderByIdDesc(String language, String mainFood, Pageable pageable);

    
    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r where e.language = :language GROUP BY e.id ORDER BY SUM(r.score) DESC")
    Page<Food> findFilteredRatings(Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r WHERE e.name LIKE %:keyword% and e.language = :language GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Food> findFilteredRatingsAndSearchName(@Param("language") String language, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r WHERE e.newAddress LIKE %:keyword% and e.language = :language GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Food> findFilteredRatingsAndSearchNewAddress(@Param("language") String language, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodRatings r WHERE e.mainFood LIKE %:keyword% and e.language = :language GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Food> findFilteredRatingsAndSearchMainFood(@Param("language") String language, @Param("keyword") String keyword, Pageable pageable);



    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r where e.language = :language GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviews(@Param("language") String language, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r WHERE e.name LIKE %:keyword%  and e.language = :language GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviewsAndSearchName(@Param("language") String language, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r WHERE e.newAddress LIKE %:keyword% and e.language = :language GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviewsAndSearchNewAddress(@Param("language") String language, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Food e LEFT JOIN e.foodReviews r WHERE e.mainFood LIKE %:keyword% and e.language = :language GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Food> findFilteredReviewsAndSearchMainFood(@Param("language") String language, @Param("keyword") String keyword, Pageable pageable);

}