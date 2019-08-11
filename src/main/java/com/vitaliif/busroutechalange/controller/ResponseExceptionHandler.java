package com.vitaliif.busroutechalange.controller;

import com.vitaliif.busroutechalange.dto.ErrorResponse;
import com.vitaliif.busroutechalange.exception.IllegalBusStationFileException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalBusStationFileException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleIllegalBusStationFileException(IllegalBusStationFileException e) {
        return new ErrorResponse(e.getMessage());

    }

}
