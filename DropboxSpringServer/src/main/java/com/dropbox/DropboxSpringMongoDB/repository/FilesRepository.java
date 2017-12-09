package com.dropbox.DropboxSpringMongoDB.repository;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesRepository extends MongoRepository<Files, String>{

//    @Autowired
//    MongoTemplate mongoTemplate;

//    List<Files> findByUser_uuidIs(String user_uuid);
//    List<Files> findAllByUser_uuid(String user_uuid);

//    @Query(value = "{ 'user_uuid' : {$all : [?0] }}")

    @Query(value = "{'user_uuid': ?0}.toArray()")
    List<Files> findAnyOfTheseValues(String user_uuid);
}
