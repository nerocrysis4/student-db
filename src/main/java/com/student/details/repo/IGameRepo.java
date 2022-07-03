package com.student.details.repo;

import com.student.details.model.collection.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepo extends ReactiveCrudRepository<Student, String> {


}
