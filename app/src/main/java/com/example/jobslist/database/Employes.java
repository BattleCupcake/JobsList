package com.example.jobslist.database;

import android.annotation.SuppressLint;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "employes_table")
public class Employes {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "f_name")
    private String f_name;

    @NonNull
    @ColumnInfo(name = "l_name")
    private String l_name;

    @NonNull
    @ColumnInfo(name = "birthday")
    private String birthday;

    @NonNull
    @ColumnInfo(name = "avatr_url")
    private String avatr_url;

    @NonNull
    @ColumnInfo(name = "specialty_id")
    private String specialty_id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Employes(@NonNull String f_name, @NonNull String l_name, @NonNull String birthday, @NonNull String avatr_url, @NonNull String specialty_id, @NonNull String name) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.birthday = birthday;
        this.avatr_url = avatr_url;
        this.specialty_id = specialty_id;
        this.name = name;
    }

    public String getF_name() {
        return firstUpperCase(f_name);
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return firstUpperCase(l_name);
    }

    private String firstUpperCase(String word) {
        word = word.toLowerCase();
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getBirthday() {

        if ( birthday != null && !birthday.equals("")) {
            String Ndate = birthday;
            String year;
            String month;
            String day;
            if (Ndate.contains("-")) {

                String[] splitted = Ndate.split("-");
                if (splitted.length == 3) {
                    if (splitted[0].length() == 4) {
                        year = splitted[0];
                        day = splitted[2];
                    } else {
                        year = splitted[2];
                        day = splitted[0];
                    }
                    month = splitted[1];
                    Ndate = day + "." + month + "." + year;
                    birthday = Ndate;
                }
            }
            return birthday;
        } else {
            return "-";
        }
    }

    @SuppressLint("NewApi")
    public String getAge() {
        DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date today = new Date();
            today.setTime(System.currentTimeMillis());
            Date birthdayDate = outputFormat.parse(birthday);
            int age = today.getYear() - birthdayDate.getYear();
            if (today.getMonth() < birthdayDate.getMonth() |
                    (today.getMonth() == birthdayDate.getMonth()
                            && today.getDay() < birthdayDate.getDay()))
                age--;
            return String.valueOf(age);
        } catch (ParseException e) {
            e.printStackTrace();
            return "-";
        }
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatr_url() {
        if (avatr_url != null) {
            if (!avatr_url.equals("")) {
                return avatr_url;
            } else {
                return "-";
            }
        }
        return "-";
    }

    public void setAvatr_url(String avatr_url) {
        this.avatr_url = avatr_url;
    }

    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
