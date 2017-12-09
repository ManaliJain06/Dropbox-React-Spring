package com.dropbox.DropboxSpringMongoDB.config;


import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
public class AppConfig {

    public @Bean MongoDbFactory mongoDbFactory(){
//        for(int i=0; i<10; i++){
            return new SimpleMongoDbFactory(new MongoClient(), "Dropboxuser");
//        }
    }

    public @Bean MongoClient mongoClient() {
        return new MongoClient("localhost");
    }
}
