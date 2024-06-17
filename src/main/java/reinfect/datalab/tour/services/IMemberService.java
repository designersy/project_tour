package reinfect.datalab.tour.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reinfect.datalab.tour.entities.Member;
import reinfect.datalab.tour.enums.RoleType;
import reinfect.datalab.tour.http.forms.MemberForgetForm;
import reinfect.datalab.tour.http.forms.MemberRegisterForm;
import reinfect.datalab.tour.http.forms.MemberUpdateForm;
import reinfect.datalab.tour.repositories.MemberRepository;
import reinfect.datalab.tour.utilities.Common;
import reinfect.datalab.tour.utilities.Notification;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IMemberService implements MemberService {

    private final Common common;
    private final Notification notification;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository repository;

    private final RoleService roleService;

    @Override
    @Transactional
    public void register(MemberRegisterForm form) throws Exception {
        try {
            String password = passwordEncoder.encode(form.getPassword());
            Member member = repository.save(
                    Member.builder().username(form.getUsername())
                                    .password(password)
                                    .nickname(form.getNickname())
                                    .email(form.getEmail())
                                    .build()
            );

            roleService.add(RoleType.USER, member);

            if (repository.count() == 1L) {
                roleService.add(RoleType.MANAGER, member);
            }
        } catch (DataAccessException exception) {
            throw new Exception("가입 처리 중 문제가 발생했습니다.");
        }
    }

    @Override
    public void update(MemberUpdateForm form, Long id) throws Exception {

    }

    @Override
    public void leave(Long id) {

    }

    @Override
    public void forget(MemberForgetForm form) throws Exception {
        Member member = repository.findByEmail(form.getEmail()).orElseThrow(() -> new Exception(common.getMessage("error.forget.data")));

        if (member.getNickname().equals(form.getNickname())) {
            String newPassword = common.randomString(6);
            String subject = common.getMessage("mail.forget");
            String template = "forget_" + common.getLocale() + ".html";

            Map<String, Object> data = new HashMap<>();
            data.put("nickname", member.getNickname());
            data.put("username", member.getUsername());
            data.put("password", newPassword);

            notification.mailSend(member.getEmail(), subject, template, data);

            member.setPassword(passwordEncoder.encode(newPassword));

            repository.save(member);
        } else {
            throw new Exception(common.getMessage("error.forget.data"));
        }
    }

    @Override
    public Member currentItem(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception(common.getMessage("error.no_data")));
    }

    @Override
    public Page<Member> paginatedItem(int page, int perPage, String searchType, String searchWord) {
        return null;
    }

}
