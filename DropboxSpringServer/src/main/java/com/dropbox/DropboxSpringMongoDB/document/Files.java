package com.dropbox.DropboxSpringMongoDB.document;

import com.mongodb.util.JSON;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document
public class Files {

    @Id
    private String _id;
    private String[] user_uuid;
    private String dir_name;
    private String dir_uuid;
    private String dir_created;
    private String star_id;
    private String owner_uuid;

    public String[] getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(String[] filesArray) {
        this.filesArray = filesArray;
    }

    private String[] filesArray;

    @Override
    public String toString() {
        return "Files{" +
                "_id='" + _id + '\'' +
                ", user_uuid=" + Arrays.toString(user_uuid) +
                ", dir_name='" + dir_name + '\'' +
                ", dir_uuid='" + dir_uuid + '\'' +
                ", dir_created='" + dir_created + '\'' +
                ", star_id='" + star_id + '\'' +
                ", owner_uuid='" + owner_uuid + '\'' +
                ", filesArray=" + Arrays.toString(filesArray) +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String[] getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String[] user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getDir_name() {
        return dir_name;
    }

    public void setDir_name(String dir_name) {
        this.dir_name = dir_name;
    }

    public String getDir_uuid() {
        return dir_uuid;
    }

    public void setDir_uuid(String dir_uuid) {
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

//    public JSON[] getFilesArray() {
//        return filesArray;
//    }

//    public void setFilesArray(JSON[] filesArray) {
//        this.filesArray = filesArray;
//    }
}
