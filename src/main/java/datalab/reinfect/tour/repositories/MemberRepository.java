package datalab.reinfect.tour.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import datalab.reinfect.tour.entities.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByUsername(String username);
	Optional<MemberEntity> findByNicknameAndEmail(String nickname, String email);
	
	boolean existsByUsername(String username);
	boolean existsByNickname(String nickname);
	boolean existsByEmail(String email);
	
}
