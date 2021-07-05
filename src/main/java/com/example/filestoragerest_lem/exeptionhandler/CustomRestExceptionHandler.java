package com.example.filestoragerest_lem.exeptionhandler;

import com.example.filestoragerest_lem.dto.ApiError;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request) {

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiError apiError = new ApiError(false, ex.getMessage());
        return handleExceptionInternal(
                ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

}
