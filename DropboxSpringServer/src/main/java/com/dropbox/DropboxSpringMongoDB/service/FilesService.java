package com.dropbox.DropboxSpringMongoDB.service;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    private final FilesRepository filesRepository;

    @Autowired
    FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public List<Files> getFiles(String user_uuid) {
        return filesRepository.findAnyOfTheseValues(user_uuid);
    }

//    public List<Files> getFiles(String user_uuid) {
//        Query x = new Query(Criteria.where("").is(userId));
//        x.fields().exclude("_id").include("todos");
//        User user = mongoTemplate.findOne(x, User.class);
//        return user.getTodos();
//    }
//}


}
