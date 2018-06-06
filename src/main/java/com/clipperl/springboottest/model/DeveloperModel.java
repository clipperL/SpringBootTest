package com.clipperl.springboottest.model;

public class DeveloperModel {
    private int id;
    private String name;
    private String site;
    private String avatar;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
