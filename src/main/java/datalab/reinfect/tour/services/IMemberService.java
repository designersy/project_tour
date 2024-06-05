package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import org.springframework.data.domain.Page;

public class IMemberService implements MemberService {

    @Override
    public void register(MemberRegisterForm form) {

    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void leave(Long id) {

    }

    @Override
    public MemberEntity currentItem(Long id) {
        return null;
    }

    @Override
    public Page<MemberEntity> paginatedItems(int page, int perPage, String searchType, String searchWord) {
        return null;
    }

}
