package com.example.jobslist.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PersonsList {

    @SerializedName("response")
    private List<PersonsModel> employe;


    public List<PersonsModel> getEmploye() {
        return employe;
    }

    public void setEmploye(List<PersonsModel> employe) {
        this.employe = employe;
    }
}
