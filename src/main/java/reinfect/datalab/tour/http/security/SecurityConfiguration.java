package reinfect.datalab.tour.http.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String[] ACCESS_PUBLIC = {
            "/",
            "/food",
            "/management/**",
            "/assets/**",
            "/vendors/**",
            "/favicon.ico"
    };

    private static final String[] ACCESS_GUEST = {
            "/member/login",
            "/member/forget",
            "/member/register"
    };

    private static final String[] ACCESS_MANAGER = {
            // 관리자 페이지 및 관련 URI 추가
    };

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

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
                    .successHandler(customAuthenticationSuccessHandler())
                    .failureHandler(new SecurityLoginFailureHandler());
        }).logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true);
        }).exceptionHandling(exception -> {
            exception.accessDeniedHandler(new SecurityAccessDeniedHandler());
        }).build();
    }

    @Bean
    protected AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/");
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        return successHandler;
    }

}
