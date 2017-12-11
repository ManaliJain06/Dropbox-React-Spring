package com.dropbox.DropboxSpringMongoDB.document;

import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class User {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UUID user_uuid;
    private JSONObject overview;
    private JSONObject interest;

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getEmail() + " " + getPassword() + " " +
                getUser_uuid() + " " + getOverview() + " " + getInterest();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public UUID getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(UUID user_uuid) {
        this.user_uuid = user_uuid;
    }

    public JSONObject getOverview() {
        return overview;
    }

    public void setOverview(JSONObject overview) {
        this.overview = overview;
    }

    public JSONObject getInterest() {
        return interest;
    }

    public void setInterest(JSONObject interest) {
        this.interest = interest;
    }
}
