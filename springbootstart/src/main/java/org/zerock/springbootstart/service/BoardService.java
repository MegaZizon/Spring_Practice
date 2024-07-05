package org.zerock.springbootstart.service;

import org.zerock.springbootstart.dto.BoardDTO;
import org.zerock.springbootstart.dto.PageRequestDTO;
import org.zerock.springbootstart.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
