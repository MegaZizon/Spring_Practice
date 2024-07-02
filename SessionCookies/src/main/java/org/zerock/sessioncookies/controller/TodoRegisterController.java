package org.zerock.sessioncookies.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.sessioncookies.dto.TodoDTO;
import org.zerock.sessioncookies.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet(name="todoRegisterController", value="/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("/todo/register GET\n");

        HttpSession session = req.getSession();

        if(session.isNew()){
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
            res.sendRedirect("/login");
            return;
        }

        if(session.getAttribute("loginInfo") == null){
            log.info("로그인한 정보가 없는 사용자");
            res.sendRedirect("/login");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER))
                .build();
        log.info("/todo/register POST");
        log.info(todoDTO);
        try{
            todoService.register(todoDTO);
        }catch(Exception e){
            e.printStackTrace();
        }
        res.sendRedirect("/todo/list");
    }

}
