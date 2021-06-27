package com.chinh.covidapp.api;

import com.chinh.covidapp.model.AllModel;
import com.chinh.covidapp.model.ConfirmModel;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by gp
 */

public interface ApiInterface {

    @GET("all")
    Call<AllModel> getAll();

    @GET("confirmed")
    Call<ConfirmModel> getConfirmed();

    @GET("deaths")
    Call<ConfirmModel> getDeaths();

    @GET("recovered")
    Call<ConfirmModel> getRecovered();

}
