package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.http.forms.ForgetForm;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import org.springframework.data.domain.Page;

public interface MemberService {

	void register(MemberRegisterForm form) throws Exception;
	void update(Long id);
	void leave(Long id);
	void forget(ForgetForm form) throws Exception;

	MemberEntity currentItem(Long id);

	Page<MemberEntity> paginatedItems(int page, int perPage, String searchType, String searchWord);
	
}
