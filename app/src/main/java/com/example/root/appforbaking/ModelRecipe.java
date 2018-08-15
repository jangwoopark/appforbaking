package com.example.root.appforbaking;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelRecipe implements Parcelable {
    @JsonProperty("image")
    private String image;
    @JsonProperty("servings")
    private int servings;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ingredients")
    private List<ModelIngredients> ingredients;
    @JsonProperty("id")
    private int id;
    @JsonProperty("steps")
    private List<ModelStep> steps;

    public ModelRecipe() {
        this.image = "";
        this.servings = 0;
        this.name = "";
        this.ingredients = new ArrayList<>();
        this.id = 0;
        this.steps = new ArrayList<>();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeInt(this.servings);
        dest.writeString(this.name);
        dest.writeList(this.ingredients);
        dest.writeInt(this.id);
        dest.writeList(this.steps);
    }

    protected ModelRecipe(Parcel in) {
        this.image = in.readString();
        this.servings = in.readInt();
        this.name = in.readString();
        this.ingredients = new ArrayList<>();
        in.readList(this.ingredients, ModelIngredients.class.getClassLoader());
        this.id = in.readInt();
        this.steps = new ArrayList<>();
        in.readList(this.steps, ModelStep.class.getClassLoader());
    }

    public static final Parcelable.Creator<ModelRecipe> CREATOR = new Parcelable.Creator<ModelRecipe>() {
        @Override
        public ModelRecipe createFromParcel(Parcel source) {
            return new ModelRecipe(source);
        }

        @Override
        public ModelRecipe[] newArray(int size) {
            return new ModelRecipe[size];
        }
    };

    public String getImage() {
        return image;
    }

    public int getServings() {
        return servings;
    }

    public String getName() {
        return name;
    }

    public List<ModelIngredients> getIngredients() {
        return ingredients;
    }

    public int getId() {
        return id;
    }

    public List<ModelStep> getSteps() {
        return steps;
    }

    public static String toBase64String(ModelRecipe recipe) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Base64.encodeToString(mapper.writeValueAsBytes(recipe), 0);
        } catch (JsonProcessingException e) {
            Logger.e(e.getMessage());
        }
        return null;
    }

    public static ModelRecipe fromBase64(String encoded) {
        if (!"".equals(encoded)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(Base64.decode(encoded, 0), ModelRecipe.class);
            } catch (IOException e) {
                Logger.e(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "image='" + image + '\'' +
                ", servings=" + servings +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", id=" + id +
                ", steps=" + steps +
                '}';
    }
}