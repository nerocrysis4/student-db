package com.game.leaderboard.config;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.game.leaderboard.model.collection.User;
import com.game.leaderboard.model.mapper.UserCsvToJson;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
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
//@EnableBatchProcessing
public class CsvToMongoJob {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

//    @Bean
//    public Job readCSVFile() {
//        return jobBuilderFactory.get("readCSVFile").incrementer(new RunIdIncrementer()).start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1").<User, User>chunk(10).reader(reader())
//                .writer(writer()).build();
//    }

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


//        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
////        reader.setResource(new ClassPathResource("ludo-data.csv"));
//        reader.setResource(new ClassPathResource("domain-data.csv"));
//        reader.setLineMapper(new DefaultLineMapper<User>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[]{"id", "name"});
////                setNames(new String[]{"_id","tx_coins","tx_gems","total_coins_after","total_gems_after","match_player","match_id","match_type",
////                        "tx_detail","app_versioncode","time_stamp","leauge_data","league_coins","match_start_time","bet_amount"
////                });
//
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
//                setTargetType(User.class);
//            }});
//        }});
//        return reader;
    }

//    @Bean
//    public MongoItemWriter<User> writer() {
//        MongoItemWriter<User> writer = new MongoItemWriter<User>();
//        writer.setTemplate(mongoTemplate);
//        writer.setCollection("user");
//        return writer;
//    }

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
