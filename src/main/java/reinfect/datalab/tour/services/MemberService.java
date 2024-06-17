package reinfect.datalab.tour.services;

import org.springframework.data.domain.Page;
import reinfect.datalab.tour.entities.Member;
import reinfect.datalab.tour.http.forms.MemberForgetForm;
import reinfect.datalab.tour.http.forms.MemberRegisterForm;
import reinfect.datalab.tour.http.forms.MemberUpdateForm;

public interface MemberService {

    void register(MemberRegisterForm form) throws Exception;
    void update(MemberUpdateForm form, Long id) throws Exception;
    void leave(Long id);

    void forget(MemberForgetForm form) throws Exception;

    Member currentItem(Long id) throws Exception;

    Page<Member> paginatedItem(int page, int perPage, String searchType, String searchWord);

}
