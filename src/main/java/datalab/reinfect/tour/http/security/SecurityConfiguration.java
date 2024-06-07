package datalab.reinfect.tour.http.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 스프링 시큐리티 설정
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * 로그인 여부 관계 없이 모든 사용자가 접근할 수 있는 페이지
     * * 에셋(CSS, 폰트, 이미지)디렉토리와 favicon 포함
     */
    private static final String[] ACCESS_PUBLIC = {
        "/",
        // 로그인 여부 관계없이 모든 사용자가 접근할 페이지 URI 추가하기
        "/assets/**",
        "/vendors/**",
        "/favicon.ico"
    };

    /**
     * 비로그인 사용자만 접근 가능한 페이지
     */
    private static final String[] ACCESS_GUEST = {
        "/member/login",
        "/member/forget/username",
        "/member/forget/password",
        "/member/register"
    };

    /**
     * 관리자만 접근 가능한 페이지
     */
    private static final String[] ACCESS_MANAGER = {
        // 관리자 페이지 및 관련 URI 추가
    };

    /**
     * 비밀번호 암호화 모듈 설정
     * @return 사용할 암호화 모듈
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 스프링 시큐리티 설정
     * @param http 시큐리티 빌더
     * @return 시큐리티 설정값
     * @throws Exception authorizeHttpRequests 에서 발생한 예외
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> {
            request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                   .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                   .requestMatchers(ACCESS_PUBLIC).permitAll()
                   .requestMatchers(ACCESS_GUEST).anonymous()
                   .requestMatchers(ACCESS_MANAGER).hasAuthority("MANAGER")
                   .anyRequest().authenticated();
        }).formLogin(login -> {
            login.loginPage("/member/login")
                 .loginProcessingUrl("/member/login")
                 .usernameParameter("username")
                 .passwordParameter("password")
                 .defaultSuccessUrl("/")
                 .failureForwardUrl("/member/login?error");
        }).logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                  .logoutSuccessUrl("/")
                  .invalidateHttpSession(true)
                  .clearAuthentication(true);
        }).exceptionHandling(exception -> {
            exception.accessDeniedHandler(new SecurityAccessDeniedHandler());
        }).build();
    }

}
