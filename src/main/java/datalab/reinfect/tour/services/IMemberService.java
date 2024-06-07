package datalab.reinfect.tour.services;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.enums.RoleType;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import datalab.reinfect.tour.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IMemberService implements MemberService {

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
