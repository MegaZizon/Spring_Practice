package org.zerock.springbootstart.controller;

import groovy.lang.GString;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springbootstart.dto.BoardDTO;
import org.zerock.springbootstart.dto.PageRequestDTO;
import org.zerock.springbootstart.dto.PageResponseDTO;
import org.zerock.springbootstart.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        log.info(responseDTO);
        log.info(pageRequestDTO.getLink());

    }

    @GetMapping("/register")
    public void registerGET() {}

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("board POST register..");

        if (bindingResult.hasErrors()) {
            log.info("has error");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/board/register";
        }
        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result",bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void read(Long bno,PageRequestDTO pageRequestDTO, Model model) {
        log.info(pageRequestDTO);
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto",boardDTO);
        log.info(pageRequestDTO.getLink());
    }
}
