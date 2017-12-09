package com.dropbox.DropboxSpringMongoDB.service;

import com.dropbox.DropboxSpringMongoDB.document.User;
import com.dropbox.DropboxSpringMongoDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userSignup(User user){
        userRepository.save(user);
    }
    public List<User> login(String email, String password ) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User checkUser(String email) {
        return userRepository.findByEmail(email);
    }

}
