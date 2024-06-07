package datalab.reinfect.tour.http.security;

import datalab.reinfect.tour.entities.MemberEntity;
import datalab.reinfect.tour.entities.RoleEntity;
import datalab.reinfect.tour.repositories.MemberRepository;
import datalab.reinfect.tour.repositories.RoleRepository;
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
        MemberEntity data = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("E-00"));

        List<RoleEntity> roles = roleRepository.findAllByMember_Id(data.getId());
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
