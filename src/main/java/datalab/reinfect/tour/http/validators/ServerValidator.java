package datalab.reinfect.tour.http.validators;

import org.springframework.stereotype.Component;

import datalab.reinfect.tour.enums.UniqueType;
import datalab.reinfect.tour.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServerValidator {

	private final MemberRepository repository;
	
	public boolean memberUniqueCheck(UniqueType type, String value) {
		return !switch(type) {
			case USERNAME -> repository.existsByUsername(value);
			case NICKNAME -> repository.existsByNickname(value);
			case EMAIL -> repository.existsByEmail(value);
		};
	}
	
}
