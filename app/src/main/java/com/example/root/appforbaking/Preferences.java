package com.example.root.appforbaking;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static final String PREFS_NAME = "prefs";

    public static void saveRecipe(Context context, ModelRecipe recipe) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.widget_recipe_key), ModelRecipe.toBase64String(recipe));

        prefs.apply();
    }

    public static ModelRecipe loadRecipe(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String recipeBase64 = prefs.getString(context.getString(R.string.widget_recipe_key), "");

        return "".equals(recipeBase64) ? null : ModelRecipe.fromBase64(prefs.getString(context.getString(R.string.widget_recipe_key), ""));
    }
}
