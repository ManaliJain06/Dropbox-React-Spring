package com.dropbox.DropboxSpringMongoDB.repository;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.document.Groups;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends MongoRepository<Groups, String> {

    @Query(value = "{'membersArray.member_uuid': ?0}.toArray()")
    List<Groups> findAnyOfTheseValues(String user_uuid);

    Groups save(Groups groups);

    Groups findBy_id(String _id);

    void deleteBy_id(String _id);
}
