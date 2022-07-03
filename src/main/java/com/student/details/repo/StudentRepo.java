package com.student.details.repo;

import com.student.details.model.collection.Subject;
import com.student.details.model.collection.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public Student getStudentByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        query.fields().exclude("_id");
        return mongoTemplate.findOne(query, Student.class);
    }

    public List<Subject> getSubjectByIds(List<String> ids) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(ids));
        query.fields().exclude("_id");
        return mongoTemplate.find(query, Subject.class);
    }

}
