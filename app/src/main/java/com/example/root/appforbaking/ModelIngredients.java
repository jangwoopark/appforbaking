package com.example.root.appforbaking;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ModelIngredients implements Parcelable {
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("measure")
    private String measure;
    @JsonProperty("ingredient")
    private String ingredient;

    public ModelIngredients() {
        this.quantity = 0;
        this.measure = "";
        this.ingredient = "";
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }

    protected ModelIngredients(Parcel in) {
        this.quantity = in.readInt();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    public static final Parcelable.Creator<ModelIngredients> CREATOR = new Parcelable.Creator<ModelIngredients>() {
        @Override
        public ModelIngredients createFromParcel(Parcel source) {
            return new ModelIngredients(source);
        }

        @Override
        public ModelIngredients[] newArray(int size) {
            return new ModelIngredients[size];
        }
    };

    // Getters
    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}