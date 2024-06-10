package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.enums.RoleType;
import datalab.reinfect.tour.http.forms.ForgetForm;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import datalab.reinfect.tour.repositories.MemberRepository;
import datalab.reinfect.tour.utilities.Common;
import datalab.reinfect.tour.utilities.Notification;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IMemberService implements MemberService {

	private final Common common;
	private final Notification notification;
    private final PasswordEncoder encoder;
    
    private final MemberRepository repository;

    private final RoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(MemberRegisterForm form) throws Exception {
        try {
            MemberEntity inserted = registerInformation(form);
            roleService.register(inserted, RoleType.USER);

            if (repository.count() == 1L) {
                roleService.register(inserted, RoleType.MANAGER);
            }
        } catch (DataAccessException exception) {
            throw new Exception("등록 처리 중 문제가 발생했습니다.");
        }
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void leave(Long id) {

    }
    
    @Override
    @Transactional
    public void forget(ForgetForm form) throws Exception {
    	MemberEntity member = repository.findByNicknameAndEmail(form.getNickname(), form.getEmail()).orElseThrow(() -> new Exception("회원 정보가 없습니다."));
    	
    	try {
	    	String rawPassword = common.randomString(6);
	    	String newPassword = encoder.encode(rawPassword);
	    	String subject = "[서울유람] 비밀번호 재발급 안내";
	    	
	    	Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", member.getUsername());
            map.put("nickname", member.getNickname());
	    	map.put("password", rawPassword);
	    	
	    	member.setPassword(newPassword);
	    	
	    	repository.save(member);
	    	notification.mailSend(form.getEmail(), subject, "forget.html", map);
    	} catch (IOException exception) {
    		throw new Exception("비밀번호 변경 안내 처리 중 문제가 발생했습니다.");
    	} catch (MessagingException exception) {
    		throw new Exception("임시 비밀번호 안내 메일 발송 중 문제가 발생했습니다.");
    	}
    }

    @Override
    public MemberEntity currentItem(Long id) {
        return null;
    }

    @Override
    public Page<MemberEntity> paginatedItems(int page, int perPage, String searchType, String searchWord) {
        return null;
    }

    private MemberEntity registerInformation(MemberRegisterForm form) {
        return repository.save(
            MemberEntity.builder().username(form.getUsername())
                                  .password(encoder.encode(form.getPassword()))
                                  .nickname(form.getNickname())
                                  .national(form.getNational())
                                  .email(form.getEmail())
                                  .build()
        );
    }

}
