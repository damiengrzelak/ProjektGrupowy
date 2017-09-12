package com.example.damian.projektgrupowy.retrofit;

import com.example.damian.projektgrupowy.model.accounts.Persons;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Damian on 12.09.2017.
 */

public interface AplicationInterface {
    @GET("bins/gg8q5")
    Call <Persons> loadData();
}
