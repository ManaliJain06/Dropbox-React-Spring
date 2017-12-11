package com.dropbox.DropboxSpringMongoDB.controller;

import com.dropbox.DropboxSpringMongoDB.document.Groups;
import com.dropbox.DropboxSpringMongoDB.document.User;
import com.dropbox.DropboxSpringMongoDB.service.GroupsService;
import com.dropbox.DropboxSpringMongoDB.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/groups")
public class GroupsController {

    private GroupsService groupsService;
    private UserService userService;

    @Autowired
    GroupsController(GroupsService groupsService, UserService userService){

        this.groupsService = groupsService;
        this.userService = userService;
    }

    @GetMapping(path="/getGroups/{user_uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getGroups(@PathVariable String user_uuid){
        System.out.println("user_uuid for group is " + user_uuid);
        List<Groups> groups= groupsService.getGroups(user_uuid);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping(path="/createGroup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createGroup(@RequestBody String payload){

        JSONObject json = new JSONObject(payload);
        System.out.println("user_uuid is " + json.getString("user_uuid"));
        System.out.println("dir_name is " + json.getString("groupName"));
        System.out.println("dir_name is " + json.getString("user_name"));
        JSONObject response = new JSONObject();

        String msg = groupsService.createGroup(json.getString("user_uuid"),
                json.getString("user_name"),json.getString("groupName") );
        if(msg.equals("success")){
            response.put("statusCode",201);
            response.put("message", "Group created");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else {
            response.put("statusCode",400);
            response.put("message", "Error occurred, try once again");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/uploadFileInGroup" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> uploadFileInDir(@RequestParam("_id") String _id,
                                                  @RequestParam("user_uuid") String user_uuid,
                                                  @RequestParam("file") MultipartFile file){
        System.out.println("_id is "+_id);
        JSONObject response = new JSONObject();
        String message = groupsService.uploadFileInGroup(_id,user_uuid, file);
        if(message.equals("success")){
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else {
            response.put("statusCode",400);
            response.put("message", "Error occurred, try once again");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/deleteGroup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteGroup(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        System.out.println("_id is  sss " + json.getString("_id"));
        String msg = groupsService.deleteGroup(json.getString("_id"));
        JSONObject response = new JSONObject();
        response.put("message",msg);
        response.put("statusCode",201);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @PostMapping(path="/deleteFileFromGroup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteFileFromGroup(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        String msg = groupsService.deleteFileFromGroup(json.getString("_id"),
                json.getString("file_uuid"));
        if(msg.equals("deleted")){
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else{
            response.put("statusCode",400);
            response.put("message", "Error Occured");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/addMember", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addMember(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        User user = userService.checkUser(json.getString("addToEmail"));
        if(user == null){
            response.put("statusCode",300);
            response.put("message", "User is not available in Dropbox");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
        else {
            String name = user.getFirstName()+ " " + user.getLastName();
            String msg = groupsService.addMembers(json.getString("_id"),
                    user.getUser_uuid().toString(), name);
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }
    @PostMapping(path="/deleteMember", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteMember(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        String msg = groupsService.deleteMember(json.getString("delete_uuid"),
                json.getString("_id"));
        if(msg.equals("deleted")){
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
        else {
            response.put("statusCode",400);
            response.put("message", "Delete Member Failed");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
