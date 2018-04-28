package com.developervishalsehgal.udacityscholarsapp.data.dataBinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.developervishalsehgal.udacityscholarsapp.R;

public class GlideBindingAdapters {

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, int imageUrl){

        Context context = view.getContext();

        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).circleCrop();

        Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);

    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, String imageUrl){

        Context context = view.getContext();

        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }


}



























