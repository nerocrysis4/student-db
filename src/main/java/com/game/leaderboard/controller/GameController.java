package com.game.leaderboard.controller;

import com.game.leaderboard.model.response.ApiResultModel;
import com.game.leaderboard.model.response.ResponseModel;
import com.game.leaderboard.service.impl.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaderboard")
public class GameController {

    @Autowired
    IGameService service;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResultModel> addUser(@PathVariable String userId) {
        try{
            ResponseModel response = service.getUserRank(userId);
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(false, response.getMsg(), response), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(true, e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
