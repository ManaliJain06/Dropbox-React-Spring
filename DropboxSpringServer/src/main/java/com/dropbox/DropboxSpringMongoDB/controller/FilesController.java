package com.dropbox.DropboxSpringMongoDB.controller;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.service.FilesService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<Files> files= filesService.getFiles("59f0af65-5ce7-4d8e-934a-a1b5d002c075");
        response.put("message","Valid user");
        response.put("statusCode",201);
        response.put("isLogged", true);
        response.put("files", files);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

}
