package com.student.details.model.responseModel;

import com.student.details.model.collection.Student;
import com.student.details.model.collection.Subject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public static Flux<StudentResponseModel> mapStudent(Flux<Student> student) {
        if (student == null)
            return null;
        return student.flatMap(obj -> {
            final StudentResponseModel studentDto = new StudentResponseModel();
            studentDto.setAge(obj.getAge());
            studentDto.setAddress(obj.getAddress());
            studentDto.setSubjects(obj.getSubjects().stream().collect(Collectors.toMap(Subject::getName, Subject::getMarks)));
            return Mono.just(studentDto);
        });
    }
}
