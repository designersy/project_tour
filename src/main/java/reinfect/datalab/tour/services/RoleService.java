package reinfect.datalab.tour.services;

import reinfect.datalab.tour.entities.Member;
import reinfect.datalab.tour.enums.RoleType;

public interface RoleService {

    void add(RoleType role, Member member);

}
