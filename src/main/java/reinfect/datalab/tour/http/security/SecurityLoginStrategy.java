package reinfect.datalab.tour.http.security;

import reinfect.datalab.tour.entities.Member;
import reinfect.datalab.tour.entities.Role;
import reinfect.datalab.tour.repositories.MemberRepository;
import reinfect.datalab.tour.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityLoginStrategy implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member data = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("E-00"));

        List<Role> roles = roleRepository.findAllByMember_Id(data.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();

        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
        });

        return User.builder().username(username)
                             .password(data.getPassword())
                             .authorities(authorities)
                             .build();
    }

}