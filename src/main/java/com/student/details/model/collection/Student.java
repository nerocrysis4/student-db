package com.student.details.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "student")
public class Student {

    @Id
    @JsonProperty("_id")
    private String id;
    private String name;
    private int age;
    private List<String> address;
    private List<String> subjects;

}
