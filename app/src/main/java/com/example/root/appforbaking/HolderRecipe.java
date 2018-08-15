package com.example.root.appforbaking;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HolderRecipe extends RecyclerView.ViewHolder {
    @BindView(R.id.recipe_name_text)
    public TextView mTvRecipeName;

    @BindView(R.id.servings_text)
    public TextView mTvServings;

    @BindView(R.id.recipe_image)
    public AppCompatImageView mIvRecipe;

    public HolderRecipe(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

}
