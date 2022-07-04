package com.student.details.service;

import com.student.details.model.collection.Student;
import com.student.details.model.responseModel.StudentResponseModel;
import com.student.details.model.response.ResponseModel;
import com.student.details.repo.IStudentRepo;
import com.student.details.service.impl.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepo repository;

    public Mono<ResponseModel> getStudentByName(String name) {
        Flux<Student> student = repository.getStudentByName(name);
        Flux<StudentResponseModel> outputModel = StudentResponseModel.mapStudent(student);

        return Mono.just(new ResponseModel(outputModel, "data fetch successfully"));
    }
}
