package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential implements DefaultModel {

    private int credentialId;

    private String url;

    private String username;

    private String password;

    private int userId;

    public int getId() {
        return credentialId;
    }

    public void setId(int credentialId) {
        this.credentialId = credentialId;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
