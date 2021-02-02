package com.enrich.behope;

public class UserHelperClass {

    String name,email,phoneno,password;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String email, String phoneno, String password) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
