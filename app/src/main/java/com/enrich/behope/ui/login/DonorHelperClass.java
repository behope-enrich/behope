package com.enrich.behope.ui.login;

public class DonorHelperClass {

    String donor_blood_group,donor_gender,donor_lst_dnt_dt,donor_name,donor_phone;

    DonorHelperClass(){

    }

    public DonorHelperClass(String donor_blood_group, String donor_gender, String donor_lst_dnt_dt, String donor_name, String donor_phone) {
        this.donor_blood_group = donor_blood_group;
        this.donor_gender = donor_gender;
        this.donor_lst_dnt_dt = donor_lst_dnt_dt;
        this.donor_name = donor_name;
        this.donor_phone = donor_phone;
    }

    public String getDonor_blood_group() {
        return donor_blood_group;
    }

    public void setDonor_blood_group(String donor_blood_group) {
        this.donor_blood_group = donor_blood_group;
    }

    public String getDonor_gender() {
        return donor_gender;
    }

    public void setDonor_gender(String donor_gender) {
        this.donor_gender = donor_gender;
    }

    public String getDonor_lst_dnt_dt() {
        return donor_lst_dnt_dt;
    }

    public void setDonor_lst_dnt_dt(String donor_lst_dnt_dt) {
        this.donor_lst_dnt_dt = donor_lst_dnt_dt;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public String getDonor_phone() {
        return donor_phone;
    }

    public void setDonor_phone(String donor_phone) {
        this.donor_phone = donor_phone;
    }
}
