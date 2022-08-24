package com.example.wehelppetsmobileapplication;

public class SearchUser {

    private String email, name, photo;


    public SearchUser() {
    }

    public SearchUser(String email, String name, String photo) {
        this.email = email;
        this.name = name;
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
