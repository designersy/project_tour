package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import org.springframework.data.domain.Page;

public interface MemberService {

	void register(MemberRegisterForm form);
	void update(Long id);
	void leave(Long id);

	MemberEntity currentItem(Long id);

	Page<MemberEntity> paginatedItems(int page, int perPage, String searchType, String searchWord);
	
}
