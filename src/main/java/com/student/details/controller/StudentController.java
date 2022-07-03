package com.student.details.controller;

import com.student.details.model.response.ApiResultModel;
import com.student.details.model.response.ResponseModel;
import com.student.details.service.impl.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/StudentDetails")
public class StudentController {

    @Autowired
    IGameService service;

    @GetMapping("/getData.htm")
    public ResponseEntity<ApiResultModel> addUser(@RequestParam String studentName) {
        try{
            ResponseModel response = service.getStudentByName(studentName);
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(false, response.getMsg(), response), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<ApiResultModel>(new ApiResultModel(true, e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
