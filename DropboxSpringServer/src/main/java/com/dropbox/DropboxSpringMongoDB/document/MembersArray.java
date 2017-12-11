package com.dropbox.DropboxSpringMongoDB.document;


import java.util.UUID;

public class MembersArray {

    private String member_uuid;
    private String member_name;

    public String getMember_uuid() {
        return member_uuid;
    }

    public void setMember_uuid(String member_uuid) {
        this.member_uuid = member_uuid;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }
}
