package datalab.reinfect.tour.repositories;

import datalab.reinfect.tour.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findAllByMember_Id(Long member);

}
