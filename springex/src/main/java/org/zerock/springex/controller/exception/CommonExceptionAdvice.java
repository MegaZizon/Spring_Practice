package org.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String excepNumber(NumberFormatException ex) {
        log.error(ex.getMessage());

        return "NUMBER FORMAT EXCEPTION";
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String excepCommon(Exception ex) {
        log.error(ex.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>").append(ex.getMessage()).append("</li>");

        Arrays.stream(ex.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>").append(stackTraceElement.toString()).append("</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }
}
