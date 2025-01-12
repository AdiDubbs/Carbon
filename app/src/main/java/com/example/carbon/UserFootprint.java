package com.example.carbon;

public class UserFootprint
{
    String id;
    String userFootprint;
    String userDate;

    public UserFootprint(String id, String userFootprint, String userDate) {
        this.id = id;
        this.userFootprint = userFootprint;
        this.userDate = userDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFootprint() {
        return userFootprint;
    }

    public void setFootprint(String userFootprint) {
        this.userFootprint = userFootprint;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }
}



