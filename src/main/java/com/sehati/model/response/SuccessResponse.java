package com.sehati.model.response;

import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends CommonResponse{
    T data;
    public SuccessResponse(String message, T data){
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = data;
    }

    public SuccessResponse(){};

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
