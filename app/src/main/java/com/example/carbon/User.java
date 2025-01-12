package com.example.carbon;

public class User {
    String userName;
    String userEmail;
    String userFootprint;
    String userPhone;
    String userEmissions;
    String userSetoff;
    String userTrees;

    public User()
    {

    }

    public User(String userName, String userEmail, String userPhone, String userFootprint, String userEmissions, String userSetoff, String userTrees) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userFootprint = userFootprint;
        this.userPhone = userPhone;
        this.userEmissions = userEmissions;
        this.userSetoff = userSetoff;
        this.userTrees = userTrees;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFootprint() {
        return userFootprint;
    }

    public void setUserFootprint(String userFootprint) {
        this.userFootprint = userFootprint;
    }

    public String getUserEmissions() {
        return userEmissions;
    }

    public void setUserEmissions(String userEmissions) { this.userEmissions = userEmissions; }

    public String getUserSetoff() { return userSetoff; }

    public void setUserSetoff(String userSetoff) {
        this.userSetoff = userSetoff;
    }

    public String getUserTrees() {
        return userTrees;
    }

    public void setUserTrees(String userTrees) {
        this.userTrees = userTrees;
    }
}
