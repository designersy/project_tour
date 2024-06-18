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

    @Query("SELECT e FROM Place e where e.language = :language order by RAND() limit 9")
    List<Place> findLatestRandom(@Param("language") String language);

    List<Place> findTop10OrderByOrderByIdDesc();


    @Query("SELECT e FROM Place e WHERE e.language = 'ko'")
    Page<Place> findAllByOrderByIdDescKo(Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'ko'")
    Page<Place> findAllByNameContainingOrderByIdDescKo(String name, Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'ko'")
    Page<Place> findAllByNewAddressContainingOrderByIdDescKo(String newAddress, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.language = 'ko' GROUP BY e.id ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsSpecificLanguageKo(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.name LIKE %:keyword% AND e.language = 'ko' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNameSpecificLanguageKo(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.newAddress LIKE %:keyword% AND e.language = 'ko' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNewAddressSpecificLanguageKo(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.language = 'ko' GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsSpecificLanguageKo(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.name LIKE %:keyword% AND e.language = 'ko' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNameSpecificLanguageKo(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.newAddress LIKE %:keyword% AND e.language = 'ko' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNewAddressSpecificLanguageKo(@Param("keyword") String keyword, Pageable pageable);



    @Query("SELECT e FROM Place e WHERE e.language = 'en'")
    Page<Place> findAllByOrderByIdDescEn(Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'en'")
    Page<Place> findAllByNameContainingOrderByIdDescEn(String name, Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'en'")
    Page<Place> findAllByNewAddressContainingOrderByIdDescEn(String newAddress, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.language = 'en' GROUP BY e.id ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsSpecificLanguageEn(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.name LIKE %:keyword% AND e.language = 'en' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNameSpecificLanguageEn(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.newAddress LIKE %:keyword% AND e.language = 'en' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNewAddressSpecificLanguageEn(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.language = 'en' GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsSpecificLanguageEn(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.name LIKE %:keyword% AND e.language = 'en' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNameSpecificLanguageEn(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.newAddress LIKE %:keyword% AND e.language = 'en' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNewAddressSpecificLanguageEn(@Param("keyword") String keyword, Pageable pageable);



    @Query("SELECT e FROM Place e WHERE e.language = 'ja'")
    Page<Place> findAllByOrderByIdDescJa(Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'ja'")
    Page<Place> findAllByNameContainingOrderByIdDescJa(String name, Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'ja'")
    Page<Place> findAllByNewAddressContainingOrderByIdDescJa(String newAddress, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.language = 'ja' GROUP BY e.id ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsSpecificLanguageJa(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.name LIKE %:keyword% AND e.language = 'ja' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNameSpecificLanguageJa(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.newAddress LIKE %:keyword% AND e.language = 'ja' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNewAddressSpecificLanguageJa(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.language = 'ja' GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsSpecificLanguageJa(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.name LIKE %:keyword% AND e.language = 'ja' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNameSpecificLanguageJa(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.newAddress LIKE %:keyword% AND e.language = 'ja' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNewAddressSpecificLanguageJa(@Param("keyword") String keyword, Pageable pageable);



    @Query("SELECT e FROM Place e WHERE e.language = 'zh-CN'")
    Page<Place> findAllByOrderByIdDescZhCN(Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'zh-CN'")
    Page<Place> findAllByNameContainingOrderByIdDescZhCN(String name, Pageable pageable);
    @Query("SELECT e FROM Place e WHERE e.language = 'zh-CN'")
    Page<Place> findAllByNewAddressContainingOrderByIdDescZhCN(String newAddress, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.language = 'zh-CN' GROUP BY e.id ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsSpecificLanguageZhCN(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.name LIKE %:keyword% AND e.language = 'zh-CN' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNameSpecificLanguageZhCN(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeRatings r WHERE e.newAddress LIKE %:keyword% AND e.language = 'zh-CN' GROUP BY e.id  ORDER BY SUM(r.score) DESC")
    Page<Place> findFilteredRatingsAndSearchNewAddressSpecificLanguageZhCN(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.language = 'zh-CN' GROUP BY e.id ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsSpecificLanguageZhCN(Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.name LIKE %:keyword% AND e.language = 'zh-CN' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNameSpecificLanguageZhCN(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Place e LEFT JOIN e.placeReviews r WHERE e.newAddress LIKE %:keyword% AND e.language = 'zh-CN' GROUP BY e.id  ORDER BY COUNT(r.id) DESC")
    Page<Place> findFilteredReviewsAndSearchNewAddressSpecificLanguageZhCN(@Param("keyword") String keyword, Pageable pageable);
}