package com.dropbox.DropboxSpringMongoDB.service;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.document.filesArray;
import com.dropbox.DropboxSpringMongoDB.repository.FilesRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    public String uploadFile(String user_uuid, MultipartFile file) {
        System.out.println("user is" + user_uuid);
        try {
            byte[] bytes = file.getBytes();
            File directory = new File("public/");
            File serverFile = new File(directory.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            System.out.println("file uploaded");

            String str = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(new Date());
            filesArray fileArray = new filesArray();
            fileArray.setFile_created(str);
            fileArray.setFile_name(file.getOriginalFilename());
            fileArray.setFile_path("http://localhost:8080/" + file.getOriginalFilename());
            fileArray.setFile_type(file.getContentType());
            fileArray.setFile_uuid(UUID.randomUUID());


            List<filesArray> filesArrayList = new ArrayList<>();
            filesArrayList.add(fileArray);

            UUID u = null;
            String[] userId = {user_uuid};
            Files fileObject = new Files();
            fileObject.setUser_uuid(userId);
            fileObject.setDir_name("");
            fileObject.setDir_uuid(u);
            fileObject.setDir_created("");
            fileObject.setStar_id("0");
            fileObject.setOwner_uuid(user_uuid);
            fileObject.setFilesArray(filesArrayList);

            Files f = filesRepository.save(fileObject);
            if(f != null)
                return "success";
            else
                return "fail";

        } catch (Exception e) {
            return "fail";
        }
    }

    public String createDirectory(String user_uuid, String dir_name ){
        List<filesArray> filesArrayList = new ArrayList<>();

        String str = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(new Date());
        String[] userId = {user_uuid};
        Files fileObject = new Files();
        fileObject.setUser_uuid(userId);
        fileObject.setDir_uuid(UUID.randomUUID());
        fileObject.setDir_name(dir_name);
        fileObject.setDir_created(str);
        fileObject.setStar_id("0");
        fileObject.setOwner_uuid(user_uuid);
        fileObject.setFilesArray(filesArrayList);

        Files f = filesRepository.save(fileObject);

        if(f != null)
            return "success";
        else
            return "fail";
    }

    public String uploadFileInDir(String _id, MultipartFile file){
        System.out.println("_id is" + _id);
        try {
            byte[] bytes = file.getBytes();
            File directory = new File("public/");
            File serverFile = new File(directory.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            System.out.println("file uploaded");

            String str = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(new Date());
            filesArray fileArray = new filesArray();
            fileArray.setFile_created(str);
            fileArray.setFile_name(file.getOriginalFilename());
            fileArray.setFile_path("http://localhost:8080/" + file.getOriginalFilename());
            fileArray.setFile_type(file.getContentType());
            fileArray.setFile_uuid(UUID.randomUUID());

            Files f = filesRepository.findBy_id(_id);
            System.out.println("file found id"+ f);
            List<filesArray> filesArrayList = f.getFilesArray();
            filesArrayList.add(fileArray);
            f.setFilesArray(filesArrayList);

            filesRepository.save(f);
            System.out.println("file object returned  is" + f);

            return "success";

        } catch (Exception e) {
            return "fail";
        }
    }

    public String deleteFileOrDirectory(String _id){
        System.out.println("_id is" + _id);
        filesRepository.deleteBy_id(_id);
//        System.out.println("msg is" + msg);
        return "delete";
    }
}