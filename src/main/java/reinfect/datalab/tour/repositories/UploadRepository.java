package reinfect.datalab.tour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reinfect.datalab.tour.entities.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
}