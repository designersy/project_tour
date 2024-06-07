package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.entities.RoleEntity;
import datalab.reinfect.tour.enums.RoleType;
import datalab.reinfect.tour.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IRoleService implements RoleService {

    private final RoleRepository repository;

    @Override
    public void register(MemberEntity member, RoleType role) {
        repository.save(
            RoleEntity.builder().member(member)
                                .role(role)
                                .build()
        );
    }

}
