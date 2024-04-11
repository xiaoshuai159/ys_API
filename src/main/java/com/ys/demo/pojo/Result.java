package com.ys.demo.pojo;

import lombok.Data;

@Data
public class Result<T>{
    private Integer code;
    private String message;
    private T data;
    private Integer total;
    private Integer pageSize;
    public Result(){};
    public Result(Integer code, String message, T data, Integer total, Integer pageSize){
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
    }
    public Result(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data, Integer total, Integer pageSize){
        return new Result<>(200,"操作成功",data,total,pageSize);
    }
    public static Result successMarker(){
        return new Result(200,"操作成功", null);
    }
    public static <T> Result<T> success(T data){
        return new Result<>(200,"操作成功", data);
    }
    public static Result error(String message){
        return new Result(1,message,null);
    }

}
