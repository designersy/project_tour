package reinfect.datalab.tour.http.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reinfect.datalab.tour.enums.MemberType;
import reinfect.datalab.tour.repositories.MemberRepository;

@Component
@RequiredArgsConstructor
public class ServerValidator {

    private final MemberRepository memberRepository;

    public boolean isUnique(MemberType type, String value) {
        return !switch(type) {
            case USERNAME -> memberRepository.existsByUsername(value);
            case NICKNAME -> memberRepository.existsByNickname(value);
            case EMAIL -> memberRepository.existsByEmail(value);
        };
    }

}
