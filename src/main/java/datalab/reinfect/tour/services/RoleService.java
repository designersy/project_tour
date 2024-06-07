package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.enums.RoleType;

public interface RoleService {

    void register(MemberEntity member, RoleType role);

}
