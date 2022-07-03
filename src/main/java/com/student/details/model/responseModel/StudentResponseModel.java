package com.student.details.model.responseModel;

import com.student.details.model.collection.Student;
import com.student.details.model.collection.Subject;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class StudentResponseModel {
    @Id
    private String name;
    private int age;
    private List<String> address;
    private Map<String, Integer> subjects;
    public static StudentResponseModel mapStudent(Student student, List<Subject> subjects){
        if(student == null)
            return null;
        StudentResponseModel user = new StudentResponseModel();
        user.setName(student.getName());
        user.setAge(student.getAge());
        user.setAddress(student.getAddress());
        user.setSubjects(subjects.stream().collect(Collectors.toMap(Subject::getName, Subject::getMarks)));
        return user;
    }
}
