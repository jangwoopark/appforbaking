package com.example.root.appforbaking;

import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class ApiManager implements Serializable {

    private static volatile ApiManager sharedInstance = new ApiManager();
    private ApiService recipesApiService;

    private ApiManager() {

        if (sharedInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        recipesApiService = retrofit.create(ApiService.class);
    }

    public static ApiManager getInstance() {
        if (sharedInstance == null) {
            synchronized (ApiManager.class) {
                if (sharedInstance == null) sharedInstance = new ApiManager();
            }
        }

        return sharedInstance;
    }

    public void getRecipes(final ApiCallBack<List<ModelRecipe>> recipesApiCallback) {
        recipesApiService.getRecipes().enqueue(new Callback<List<ModelRecipe>>() {
            @Override
            public void onResponse(Call<List<ModelRecipe>> call, Response<List<ModelRecipe>> response) {
                recipesApiCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelRecipe>> call, Throwable t) {
                if (call.isCanceled()) {
                    Logger.e("Request was cancelled");
                    recipesApiCallback.onCancel();
                } else {
                    Logger.e(t.getMessage());
                    recipesApiCallback.onResponse(null);
                }
            }
        });
    }
}

