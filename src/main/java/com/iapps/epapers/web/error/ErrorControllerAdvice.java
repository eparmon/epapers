package com.iapps.epapers.web.error;

import com.iapps.epapers.web.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xml.sax.SAXException;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(SAXException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleSAXException(SAXException ex) {
        return new ErrorDto("Invalid XML");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGeneralException(SAXException ex) {
        ex.printStackTrace();
        return new ErrorDto("Something went wrong");
    }

}
