package com.dropbox.DropboxSpringMongoDB.repository;

import com.dropbox.DropboxSpringMongoDB.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    User save(User saved);

    List<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    User findBy_id(String _id);
}
