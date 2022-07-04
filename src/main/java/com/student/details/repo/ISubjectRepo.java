package com.student.details.repo;

import com.student.details.model.collection.Student;
import com.student.details.model.collection.Subject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubjectRepo extends ReactiveCrudRepository<Subject, String> {
    List<Subject> getSubjectByIds(List<String> ids);
}
