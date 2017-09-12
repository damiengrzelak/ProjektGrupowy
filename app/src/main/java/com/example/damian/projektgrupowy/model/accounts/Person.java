package com.example.damian.projektgrupowy.model.accounts;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.damian.projektgrupowy.model.achivments.Achievement;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Damian on 14.04.2017.
 */

public class Person extends BaseModel implements Parcelable{
    @PrimaryKey
    @Column
    @SerializedName("id")
    int id;

    @Column
    @SerializedName("name")
    String name;

    @Column
    @SerializedName("lastName")
    String lastName;

    @Column
    @SerializedName("photo")
    String photo;

    @Column
    @SerializedName("login")
    String login;

    @Column
    @SerializedName("password")
    String password;

    @Column
    @SerializedName("email")
    String email;

    @Column
    @SerializedName("address")
    String adres;

    @Column
    @SerializedName("achivements")
    Achievement achievemens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Achievement getAchievemens() {
        return achievemens;
    }

    public void setAchievemens(Achievement achievemens) {
        this.achievemens = achievemens;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
