package com.example.filestoragerest_lem.exeptionhandler;

import com.example.filestoragerest_lem.dto.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        HttpMethod httpMethod = ((ServletWebRequest) request).getHttpMethod();
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String error = "Unknown Exception";

        if (httpMethod == HttpMethod.POST && "/file".equals(requestURI)) {
            error = ex.getMessage();
        } else if (httpMethod == HttpMethod.DELETE && Objects.nonNull(requestURI) && requestURI.endsWith("/tags")) {
            error = "tag not found on file";
        } else if (httpMethod == HttpMethod.DELETE) {
            error = "file not found";
        }


        ApiError apiError =
                new ApiError(false, error);
        return handleExceptionInternal(
                ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }
}
