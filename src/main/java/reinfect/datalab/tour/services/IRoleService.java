package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reinfect.datalab.tour.entities.Member;
import reinfect.datalab.tour.entities.Role;
import reinfect.datalab.tour.enums.RoleType;
import reinfect.datalab.tour.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class IRoleService implements RoleService {

    private final RoleRepository repository;

    @Override
    public void add(RoleType role, Member member) {
        repository.save(
            Role.builder().role(role).member(member).build()
        );
    }

}
