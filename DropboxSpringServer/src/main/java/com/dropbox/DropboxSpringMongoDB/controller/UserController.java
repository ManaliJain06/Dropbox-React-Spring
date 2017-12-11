package com.dropbox.DropboxSpringMongoDB.controller;

import com.dropbox.DropboxSpringMongoDB.document.User;
import com.dropbox.DropboxSpringMongoDB.service.UserService;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<String> addNewUser (@RequestBody User user) {

        JSONObject response = new JSONObject();
        System.out.println("the user is "+ user.toString());
        User payload = new User();
        payload.setFirstName(user.getFirstName());
        payload.setLastName(user.getLastName());
        payload.setEmail(user.getEmail());
        payload.setPassword(user.getPassword());
        payload.setUser_uuid(UUID.randomUUID());
        payload.setInterest(null);
        payload.setOverview(null);

        User userFound = userService.checkUser(user.getEmail());

        if(userFound == null){
            userService.userSignup(payload);

            response.put("message","Successfully Registered. Please login with your credentials");
            response.put("statusCode",201);

            System.out.println("Saved");
            return new ResponseEntity<>(response.toString(), HttpStatus.ACCEPTED);
        } else {
            System.out.println("user found  is" + userFound.toString());
            response.put("message","UserName already exists");
            response.put("statusCode",401);
            return new ResponseEntity<>(response.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<String> login (@RequestBody User user) {

        JSONObject response = new JSONObject();
        System.out.println(user.getPassword() + " " + user.getEmail());
//        List<User> userRes = userService.login(user.getEmail(), user.getPassword());
        User loggedInUser  = userService.checkUser(user.getEmail());

        if(loggedInUser != null){
            if(loggedInUser.getPassword().equals(user.getPassword())){
                JSONObject jsonObject = new JSONObject(loggedInUser);
                response.put("message","Valid user");
                response.put("statusCode",201);
                response.put("isLogged", true);
                response.put("payload", jsonObject);
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } else {
                response.put("message","Invalid password");
                response.put("statusCode",400);
                response.put("isLogged", false);
                return new ResponseEntity<>(response.toString(), HttpStatus.UNAUTHORIZED);
            }
        } else{
            response.put("message","Invalid Username");
            response.put("statusCode",400);
            response.put("isLogged", false);
            return new ResponseEntity<>(response.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path="/about", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<String> postAboutUser (@RequestBody String payload){
        JSONObject response = new JSONObject(payload);

//        "{work: response.getString("work"),education: response.getString("education"),
//                "education": req.education,
//                "phone": req.phone,
//                "events": req.events
//        String request = "{music : req.music,\n" +
//                "        \"sports\" : req.sports,\n" +
//                "        \"shows\" : req.shows,}";
        String res = userService.updateAboutUser(response);
        if(res.equals("updated")){
            response.put("message","Saved Successfully");
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else{
            response.put("message","Error Occurred");
            response.put("statusCode",400);
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/interest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<String> postInterest (@RequestBody String payload){
        JSONObject response = new JSONObject(payload);

        String res = userService.updateInterest(response);
        if(res.equals("updated")){
            response.put("message","Saved Successfully");
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else{
            response.put("message","Error Occurred");
            response.put("statusCode",400);
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

}
