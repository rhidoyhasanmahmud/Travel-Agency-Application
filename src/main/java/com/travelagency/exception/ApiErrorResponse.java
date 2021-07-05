package com.travelagency.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiErrorResponse {

    private HttpStatus statusName;
    private String message;
    private List<String> errors;
    private Integer status;

    public ApiErrorResponse() {
        super();
    }

    public ApiErrorResponse(final HttpStatus statusName, final Integer status, final String message, final List<String> errors) {
        super();
        this.statusName = statusName;
        this.message = message;
        this.errors = errors;
        this.status = status;
    }

    public ApiErrorResponse(final HttpStatus statusName, final Integer status, final String message, final String error) {
        super();
        this.statusName = statusName;
        this.message = message;
        this.status = status;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatusName() {
        return statusName;
    }

    public void setStatusName(final HttpStatus statusName) {
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

}
