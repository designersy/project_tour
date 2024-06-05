package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
