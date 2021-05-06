package com.enrich.behope;

public class UserHelperClass {


    
    String name,email,phoneno,password,donate,blood_group,age,gender,last_donated_date;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String email, String phoneno, String password, String donate,String blood_group,String age,String gender,String last_donated_date) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.password = password;
        this.donate = donate;
        this.blood_group = blood_group;
        this.age = age;
        this.gender = gender;
        this.last_donated_date = last_donated_date;
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



    public String getDonate() {
        return donate;
    }

    public void setDonate(String donate) {
        this.donate = donate;
    }

    public String getblood_group() {
        return blood_group;
    }

    public void setblood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getage() {
        return age;
    }

    public void setage(String age) {
        this.age = age;
    }

    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getlast_donated_date() {
        return last_donated_date;
    }

    public void setlast_donated_date(String last_donated_date) {
        this.last_donated_date = last_donated_date;
    }

}
