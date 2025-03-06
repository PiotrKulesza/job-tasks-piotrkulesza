package com.piotrkulesza.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ErrorModel {
    private Integer status;
    private String message;

    public ErrorModel(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorModel() {}


    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
