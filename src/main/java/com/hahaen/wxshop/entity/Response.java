package com.hahaen.wxshop.entity;

public class Response<T> {
    private String message;
    private T data;

    public static <T> Response<T> of(String message, T data) {
        return new Response(message, data);
    }

    public static <T> Response<T> of(T data) {
        return new Response(null, data);
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
