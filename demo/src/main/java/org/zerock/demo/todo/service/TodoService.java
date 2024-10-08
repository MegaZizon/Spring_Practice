package org.zerock.demo.todo.service;

import com.sun.tools.javac.comp.Todo;
import org.zerock.demo.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public enum TodoService {
    INSTANCE;

    public void register(Todo todoDTO) {
        System.out.println("DEBUG........"+todoDTO);
    }

    public List<TodoDTO> getList() {
        List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i->{
            TodoDTO dto = new TodoDTO();
            dto.setTno((long)i);
            dto.setTitle("Todo.."+i);
            dto.setDueDate(LocalDate.now());

            return dto;
        }).collect(Collectors.toList());

        return todoDTOS;
    }

    public TodoDTO get(long tno){
        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);

        return dto;
    }
}
