package com.student.details.controller;

import com.student.details.model.response.ApiResultModel;
import com.student.details.model.response.ResponseModel;
import com.student.details.service.impl.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/StudentDetails")
public class StudentController {

    @Autowired
    IStudentService service;

    @GetMapping("/getData.htm")
    public ResponseEntity<Mono<ApiResultModel>> getData(@RequestParam String studentName) {
        Mono<ResponseModel> response = service.getStudentByName(studentName);
        Mono<ApiResultModel> apiResultModelMono =  response.flatMap(obj -> Mono.just(new ApiResultModel(false, obj.getMsg(), obj)));
        return new ResponseEntity<>(apiResultModelMono, HttpStatus.OK);
    }
}
