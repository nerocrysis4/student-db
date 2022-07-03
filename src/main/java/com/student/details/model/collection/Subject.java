package com.student.details.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class Subject {

    @Id
    @JsonProperty("_id")
    private String name;
    private int marks;

}
