package com.student.details.service;

import com.student.details.model.collection.Student;
import com.student.details.model.collection.Subject;
import com.student.details.model.responseModel.StudentResponseModel;
import com.student.details.model.response.ResponseModel;
import com.student.details.repo.StudentRepo;
import com.student.details.service.impl.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class StudentService implements IStudentService {

    @Autowired
    StudentRepo repository;

    public ResponseModel getStudentByName(String name) {
        Student student = repository.getStudentByName(name);
        List<Subject> subjects = repository.getSubjectByIds(student.getSubjects());
        StudentResponseModel outputModel = StudentResponseModel.mapStudent(student, subjects);

        return new ResponseModel(outputModel, "data fetch successfully");
    }
}
