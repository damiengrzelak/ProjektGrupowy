package com.example.damian.projektgrupowy.model.accounts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Damian on 12.09.2017.
 */

public class Persons {
    @SerializedName("data")
    @Expose
    private List<Person> loadPerson;

    public List<Person> getLoadData() {
        return loadPerson;
    }
}
