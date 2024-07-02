package org.zerock.sessioncookies.controller;

import lombok.extern.java.Log;
import org.zerock.sessioncookies.dto.MemberDTO;
import org.zerock.sessioncookies.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;


@WebServlet("/login")
@Log
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("login get......\n");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, res);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("login post......\n");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");



        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);

            if(rememberMe){
                String uuid = UUID.randomUUID().toString();
                MemberService.INSTANCE.updateUuid(mid,uuid);
                memberDTO.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*7);
                rememberCookie.setPath("/");

                res.addCookie(rememberCookie);
            }

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            res.sendRedirect("/todo/list");
        }catch(Exception e){
            res.sendRedirect("/login?result=error");
        }
    }
}
