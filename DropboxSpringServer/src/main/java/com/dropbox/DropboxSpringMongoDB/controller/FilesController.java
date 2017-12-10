package com.dropbox.DropboxSpringMongoDB.controller;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.service.FilesService;
import com.mongodb.util.JSON;
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

    @Autowired
    FilesController(FilesService filesService){
        this.filesService = filesService;
    }

    @GetMapping(path="/getFiles/{user_uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getFiles(@PathVariable String user_uuid){
        System.out.println("user_uuid is " + user_uuid);
        JSONObject response = new JSONObject();
        String user[] = {user_uuid};
        List<Files> files= filesService.getFiles(user_uuid);
        response.put("message","Valid user");
        response.put("statusCode",201);
        response.put("isLogged", true);
        response.put("files", files);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
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
    public ResponseEntity<String> uploadFileInDir(@RequestParam("dir_uuid") String dir_uuid,
                                                  @RequestParam("file") MultipartFile file){
        System.out.println("_id is "+dir_uuid);
        JSONObject response = new JSONObject();
        String msg = filesService.uploadFileInDir(dir_uuid,file);
        if(msg.equals("success")){
            response.put("statusCode",201);
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } else {
            response.put("statusCode",400);
            response.put("message", "Error occurred, try once again");
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}