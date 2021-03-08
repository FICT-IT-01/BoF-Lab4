package com.kpi.it01.team1.models;

import javax.servlet.http.HttpServletRequest;

public class RequestValidationModel<T> {
    private T model;
    private HttpServletRequest request;

    public RequestValidationModel(T model, HttpServletRequest request) {
        this.model = model;
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public T getModel() {
        return model;
    }
}
