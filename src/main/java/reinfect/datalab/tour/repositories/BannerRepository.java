package reinfect.datalab.tour.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    Page<Banner> findAllByOrderByIdDesc(Pageable pageable);

}