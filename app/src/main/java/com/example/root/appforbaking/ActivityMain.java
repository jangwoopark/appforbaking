package com.example.root.appforbaking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityMain extends AppCompatActivity implements FragmentDetails.OnRecipeClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRecipeSelected(ModelRecipe recipe) {
        Intent intent = new Intent(this, ActivityRecipe.class);
        intent.putExtra(ActivityRecipe.RECIPE_KEY, recipe);
        startActivity(intent);
    }

}
