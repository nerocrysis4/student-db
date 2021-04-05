package com.game.leaderboard.config;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.game.leaderboard.model.collection.User;
import com.game.leaderboard.model.mapper.UserCsvToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Configuration
public class CsvToMongoJob {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Bean
    public void csvToObject() {
        List<UserCsvToJson> userList = loadObjectList(UserCsvToJson.class, "ludo-data.csv");

        List<User> sortedUser =  User.mapUserList(userList).stream()
                .filter(obj -> obj.getTimeStamp() != null)
                .collect(groupingBy(User::getId,
                        Collectors.collectingAndThen(
                                Collectors.reducing((User d1, User d2) ->
                                                (d1.getTimeStamp().after(d2.getTimeStamp())) ? d1 : d2),
                                Optional::get)))
                                .values().stream().collect(Collectors.toList());

        mongoTemplate.remove(new Query(), User.class);
        mongoTemplate.insertAll(sortedUser);

    }


    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
