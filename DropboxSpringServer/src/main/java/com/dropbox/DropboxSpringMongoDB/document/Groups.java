package com.dropbox.DropboxSpringMongoDB.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class Groups {

    @Id
    private String _id;
    private UUID group_uuid;
    private String group_name;
    private String creator_uuid;
    private String creator_name;
    private List<MembersArray> membersArray;
    private List<filesArray> filesArray;

    @Override
    public String toString() {
        return "Groups{" +
                "_id='" + _id + '\'' +
                ", group_uuid=" + group_uuid +
                ", group_name='" + group_name + '\'' +
                ", creator_uuid='" + creator_uuid + '\'' +
                ", creator_name='" + creator_name + '\'' +
                ", membersArrays=" + membersArray +
                ", filesArray=" + filesArray +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public UUID getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(UUID group_uuid) {
        this.group_uuid = group_uuid;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getCreator_uuid() {
        return creator_uuid;
    }

    public void setCreator_uuid(String creator_uuid) {
        this.creator_uuid = creator_uuid;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public List<MembersArray> getMembersArray() {
        return membersArray;
    }

    public void setMembersArray(List<MembersArray> membersArray) {
        this.membersArray = membersArray;
    }

    public List<com.dropbox.DropboxSpringMongoDB.document.filesArray> getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(List<com.dropbox.DropboxSpringMongoDB.document.filesArray> filesArray) {
        this.filesArray = filesArray;
    }
}
