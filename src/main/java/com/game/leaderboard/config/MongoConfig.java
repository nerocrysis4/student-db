//package com.game.leaderboard.config;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoClientFactoryBean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//     @Bean
//     public MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setHost("localhost");
//        return mongo;
//    }
//
//    @Override
//    public String getDatabaseName() {
//        return "database";
//    }
//
//    @Override
//    protected void configureClientSettings(MongoClientSettings.Builder builder) {
//
//        builder
//                .credential(MongoCredential.createCredential("name", "db", "pwd".toCharArray()))
//                .applyToClusterSettings(settings  -> {
//                    settings.hosts(singletonList(new ServerAddress("127.0.0.1", 27017)));
//                });
//    }
//
//    public @Bean MongoClient mongoClient() {
//        return MongoClients.create("mongodb://localhost:27017");
//    }
//
//    public @Bean MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), "mydatabase");
//    }
//}
