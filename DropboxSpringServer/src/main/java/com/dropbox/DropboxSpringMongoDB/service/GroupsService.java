package com.dropbox.DropboxSpringMongoDB.service;

import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.document.Groups;
import com.dropbox.DropboxSpringMongoDB.document.MembersArray;
import com.dropbox.DropboxSpringMongoDB.document.filesArray;
import com.dropbox.DropboxSpringMongoDB.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GroupsService {

    private final GroupsRepository groupsRepository;

    @Autowired
    GroupsService(GroupsRepository groupsRepository){
        this.groupsRepository = groupsRepository;
    }

    public List<Groups> getGroups(String user_uuid) {
        return groupsRepository.findAnyOfTheseValues(user_uuid);
    }

    public String createGroup(String user_uuid, String user_name, String groupName ){
        List<filesArray> filesArrayList = new ArrayList<>();
        List<MembersArray> membersArrayList = new ArrayList<>();

        MembersArray member = new MembersArray();
        member.setMember_name(user_name);
        member.setMember_uuid(user_uuid);

        membersArrayList.add(member);

        Groups group = new Groups();
        group.setGroup_uuid(UUID.randomUUID());
        group.setGroup_name(groupName);
        group.setCreator_uuid(user_uuid);
        group.setCreator_name(user_name);
        group.setMembersArray(membersArrayList);
        group.setFilesArray(filesArrayList);

        Groups g = groupsRepository.save(group);
        System.out.println("g is "+ g);
        if(g != null)
            return "success";
        else
            return "fail";
    }

    public String uploadFileInGroup(String _id, String user_uuid, MultipartFile file){
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
            fileArray.setOwner_uuid(user_uuid);

            Groups g = groupsRepository.findBy_id(_id);
            System.out.println("file found id"+ g);
            List<filesArray> filesArrayList = g.getFilesArray();
            filesArrayList.add(fileArray);
            g.setFilesArray(filesArrayList);

            groupsRepository.save(g);
            System.out.println("file object returned  is" + g);

            return "success";

        } catch (Exception e) {
            return "fail";
        }
    }

    public String deleteGroup(String _id){
        System.out.println("_id is" + _id);
        groupsRepository.deleteBy_id(_id);
        return "deleted";
    }
}
