package com.dropbox.DropboxSpringMongoDB.controller;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.service.FilesService;
import com.dropbox.DropboxSpringMongoDB.service.UserService;
import com.dropbox.DropboxSpringMongoDB.document.User;
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
@RequestMapping(path="/files")
public class FilesController {

    private FilesService filesService;
    private UserService userService;

    @Autowired
    FilesController(FilesService filesService, UserService userService){
        this.filesService = filesService;
        this.userService = userService;
    }


    @GetMapping(path="/getFiles/{user_uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getFiles(@PathVariable String user_uuid){
        System.out.println("user_uuid is " + user_uuid);
        List<Files> files= filesService.getFiles(user_uuid);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @PostMapping(path="/uploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("user_uuid") String user_uuid,
                                        @RequestParam("file") MultipartFile file) {

        JSONObject response = new JSONObject();

        if (!file.isEmpty()) {
            String message = filesService.uploadFile(user_uuid, file);
            if(message.equals("success")){
                response.put("statusCode",201);
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } else{
                response.put("statusCode",400);
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            }
        } else {
            response.put("statusCode",400);
            response.put("message","Don't upload an empty file");
            return new ResponseEntity<>(response.toString(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/createDir", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createDirectory(@RequestBody String payload){

        JSONObject json = new JSONObject(payload);
        System.out.println("user_uuid is " + json.getString("user_uuid"));
        System.out.println("dir_name is " + json.getString("dir_name"));
        JSONObject response = new JSONObject();

        String msg = filesService.createDirectory(json.getString("user_uuid"),json.getString("dir_name"));
        if(msg.equals("success")){
            response.put("statusCode",201);
            response.put("message", "Directory created");
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else {
            response.put("statusCode",400);
            response.put("message", "Error occurred, try once again");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/uploadFileInDir" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> uploadFileInDir(@RequestParam("_id") String _id,
                                                  @RequestParam("file") MultipartFile file){
        System.out.println("_id is "+_id);
        JSONObject response = new JSONObject();
        String msg = filesService.uploadFileInDir(_id,file);
        if(msg.equals("success")){
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else {
            response.put("statusCode",400);
            response.put("message", "Error occurred, try once again");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/getLinks/{user_uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getLinks(@PathVariable String user_uuid){
        System.out.println("user_uuid is " + user_uuid);
//        List<Files> files= filesService.getLinks(user_uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path="/deleteFileAndDir", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteFileOrDir(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        System.out.println("_id is  sss " + json.getString("_id"));
        String msg = filesService.deleteFileOrDirectory(json.getString("_id"));
        JSONObject response = new JSONObject();
        response.put("message",msg);
        response.put("statusCode",201);
        response.put("isLogged", true);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);

    }

    @PostMapping(path="/shareFileOrDir", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> shareFileOrDir(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        User user = userService.checkUser(json.getString("shareToEmail"));
        if(user == null){
            response.put("statusCode",300);
            response.put("message", "User is not available in Dropbox");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
        else {
            String msg = filesService.shareFileOrDirectory(json.getString("_id"),
                    user.getUser_uuid().toString());
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }

    @PostMapping(path="/starFileOrDir", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> starFileOrDir(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        String msg = filesService.StarFilesOrDir(json.getString("_id"));
        if(msg.equals("starred")){
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else{
            response.put("statusCode",400);
            response.put("message", "Error Occured");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path="/deleteFileFromDir", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteFileFromDir(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        JSONObject response = new JSONObject();
        String msg = filesService.deleteFileFromDir(json.getString("_id"),
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

}
