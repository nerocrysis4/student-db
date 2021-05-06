package com.game.leaderboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.leaderboard.model.inputModel.AudioInputModel;
import com.game.leaderboard.model.response.ApiResultModel;
import com.game.leaderboard.model.response.ResponseModel;
import com.game.leaderboard.service.impl.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping(value = "/upload")
    public ResponseEntity<ApiResultModel> upload(@RequestPart("file") MultipartFile file,
                                                    @RequestPart String jsonData) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            AudioInputModel inputModel = mapper.readValue(jsonData, AudioInputModel.class);
            ResponseModel response = service.upload(file, inputModel);
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(false, response.getMsg(), response), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(true, e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping(value = "/download/{type}/{id}")
    public ResponseEntity<ApiResultModel> download(@PathVariable String type,
                                                    @PathVariable(required = false) String id) {
        try{
            ObjectMapper mapper = new ObjectMapper();
//            AudioInputModel inputModel = mapper.readValue(jsonData, AudioInputModel.class);
            ResponseModel response = service.download(type, id);
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(false, response.getMsg(), response), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(true, e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
