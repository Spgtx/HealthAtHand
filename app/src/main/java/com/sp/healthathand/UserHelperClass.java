package com.sp.healthathand;

public class UserHelperClass {
    String name, username, email, phone_no, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String email, String phone_no, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone_no = phone_no;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
