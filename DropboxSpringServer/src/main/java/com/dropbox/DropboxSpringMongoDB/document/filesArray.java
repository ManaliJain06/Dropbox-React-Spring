package com.dropbox.DropboxSpringMongoDB.document;

import java.util.Date;
import java.util.UUID;

public class filesArray {
    private UUID file_uuid;
    private String file_created;
    private String file_name;
    private String file_type;
    private String file_path;
    private String owner_uuid;

    public String getOwner_uuid() {
        return owner_uuid;
    }

    public void setOwner_uuid(String owner_uuid) {
        this.owner_uuid = owner_uuid;
    }

    public UUID getFile_uuid() {
        return file_uuid;
    }

    public void setFile_uuid(UUID file_uuid) {
        this.file_uuid = file_uuid;
    }

    public String getFile_created() {
        return file_created;
    }

    public void setFile_created(String file_created) {
        this.file_created = file_created;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
