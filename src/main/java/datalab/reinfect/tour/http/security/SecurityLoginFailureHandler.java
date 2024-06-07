package datalab.reinfect.tour.http.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class SecurityLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = "처리 중 문제가 발생했습니다";

        if (exception.getMessage().equals("E-00")) {
            message = "아이디 또는 비밀번호를 다시 한 번 확인해 보십시오.";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("message", message);

        response.sendRedirect("/member/login");
    }

}
