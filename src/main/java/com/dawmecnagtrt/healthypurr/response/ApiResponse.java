package com.dawmecnagtrt.healthypurr.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    private String status;
    private String code;
    private String message;
    private T data;

    public ApiResponse(String status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
    public ApiResponse(String status, String code, String message, T data){
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
