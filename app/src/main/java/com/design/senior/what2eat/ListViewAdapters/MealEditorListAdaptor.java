package com.design.senior.what2eat.ListViewAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.R;

import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

public class MealEditorListAdaptor extends RecyclerView.Adapter<MealEditorListAdaptor.ViewHolder> {

    private List<Meal> dataSource;
    private Context context;

    public MealEditorListAdaptor(List<Meal> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.meal_editor_list_name);
            this.thumbnail = (ImageView) view.findViewById(R.id.meal_editor_list_thumbnail);
        }
    }

    @Override
    public MealEditorListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.editor_listview_layout, parent, false);

        ViewHolder vh = new ViewHolder(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String titleString = dataSource.get(position).getName();
        byte[] imageByteArray = dataSource.get(position).getPicture();

        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

        holder.name.setText(titleString);
        holder.thumbnail.setImageBitmap(imageBitmap);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
