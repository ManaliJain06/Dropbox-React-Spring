package com.dropbox.DropboxSpringMongoDB.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Document
public class Files {

    @Id
    private String _id;
    private List<String> user_uuid;
    private String dir_name;
    private UUID dir_uuid;
    private String dir_created;
    private String star_id;
    private String owner_uuid;
    private List<filesArray> filesArray;


    @Override
    public String toString() {
        return "Files{" +
                "_id='" + _id + '\'' +
                ", user_uuid=" + user_uuid +
                ", dir_name='" + dir_name + '\'' +
                ", dir_uuid='" + dir_uuid + '\'' +
                ", dir_created='" + dir_created + '\'' +
                ", star_id='" + star_id + '\'' +
                ", owner_uuid='" + owner_uuid + '\'' +
                ", filesArray=" + filesArray +
                '}';
    }

    public List<com.dropbox.DropboxSpringMongoDB.document.filesArray> getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(List<com.dropbox.DropboxSpringMongoDB.document.filesArray> filesArray) {
        this.filesArray = filesArray;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(List<String> user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getDir_name() {
        return dir_name;
    }

    public void setDir_name(String dir_name) {
        this.dir_name = dir_name;
    }

    public UUID getDir_uuid() {
        return dir_uuid;
    }

    public void setDir_uuid(UUID dir_uuid) {
        this.dir_uuid = dir_uuid;
    }

    public String getDir_created() {
        return dir_created;
    }

    public void setDir_created(String dir_created) {
        this.dir_created = dir_created;
    }

    public String getStar_id() {
        return star_id;
    }

    public void setStar_id(String star_id) {
        this.star_id = star_id;
    }

    public String getOwner_uuid() {
        return owner_uuid;
    }

    public void setOwner_uuid(String owner_uuid) {
        this.owner_uuid = owner_uuid;
    }


}
