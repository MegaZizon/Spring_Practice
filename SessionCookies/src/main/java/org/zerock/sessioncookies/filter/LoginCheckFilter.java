package org.zerock.sessioncookies.filter;


import lombok.extern.log4j.Log4j2;
import org.zerock.sessioncookies.dto.MemberDTO;
import org.zerock.sessioncookies.service.MemberService;

import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@WebFilter(urlPatterns = "/todo/*")
@Log4j2
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("Login check filter...\n");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if((session.getAttribute("loginInfo") != null)) {
            chain.doFilter(request, response);
            return;
        }

        Cookie cookie = findCookie(req.getCookies(), "remember-me");

        if(cookie==null){
            res.sendRedirect("/login");
            return;
        }
        log.info("cookie는 존재하는 상황");
        String uuid = cookie.getValue();

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);

            log.info("쿠키의 값으로 조회한 사용자 정보 : "+memberDTO);
            if(memberDTO==null){
                throw new Exception("Cookie value is not valid");
            }
            session.setAttribute("loginInfo", memberDTO);
            chain.doFilter(request, response);
        }catch(Exception e){
            e.printStackTrace();
            res.sendRedirect("/login");
        }

    }
    private Cookie findCookie(Cookie[] cookies, String name) {
        if(cookies == null ||  cookies.length == 0) return null;

        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(ck->ck.getName().equals(name))
                .findFirst();

        return result.isPresent()?result.get():null;
    }
}
