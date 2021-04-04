package com.game.leaderboard.model.response;

import lombok.Data;

@Data
public class ResponseModel<T> {

    public String msg ;
    public T data;

    public ResponseModel(T data, String msg)
    {
        this.msg = msg;
        this.data = data;
    }

    public ResponseModel(String msg)
    {
        this.msg = msg;
    }
}

