package com.book.practice.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseDto<T> {

    private String result;
    private T data;
    private String msg;

    public ResponseDto<T> setData(T data){
        if(data == null) {
            result = "FAIL";
        } else {
            this.data = data;
            result = "SUCCESS";
        }

        return this;
    }

    public ResponseDto<T> setMsg(T data ,String msg){
        if(data == null) {
            result = "FAIL";
        } else {
            this.data = data;
            result = "SUCCESS";
        }

        return this;
    }

    public static <T> ResponseDto<T> success(T data) {
        return ResponseDto.<T>builder()
                .result("SUCCESS")
                .data(data)
                .build();
    }

    public static <T> ResponseDto<T> fail(String msg) {
        return ResponseDto.<T>builder()
                .result("FAIL")
                .msg(msg)
                .build();
    }
}
