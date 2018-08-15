package com.example.root.appforbaking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {

    @GET("http://go.udacity.com/android-baking-app-json/")
    Call<List<ModelRecipe>> getRecipes();

}
