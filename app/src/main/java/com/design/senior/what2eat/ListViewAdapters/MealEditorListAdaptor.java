package com.design.senior.what2eat.ListViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.MealViewerActivity;
import com.design.senior.what2eat.R;

import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

public class MealEditorListAdaptor extends RecyclerView.Adapter<MealEditorListAdaptor.ViewHolder> {

    private List<Meal> dataSource;
    private Context context;
    private AppCompatActivity activity;

    public MealEditorListAdaptor(AppCompatActivity activity, List<Meal> dataSource, Context context) {
        this.activity = activity;
        this.dataSource = dataSource;
        this.context = context;
    }

    public interface ClickListener {
        void onPositionClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected ImageView thumbnail;
        protected Button viewButton;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.meal_editor_list_name);
            this.thumbnail = (ImageView) view.findViewById(R.id.meal_editor_list_thumbnail);
            this.viewButton = (Button) view.findViewById(R.id.meal_editor_list_view_meal);

            viewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Meal meal = dataSource.get(getAdapterPosition());

                    Intent intent = new Intent(context, MealViewerActivity.class);
                    intent.putExtra("meal", meal);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public MealEditorListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.editor_listview_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meal meal = dataSource.get(position);

        String titleString = meal.getName();
        byte[] imageByteArray = meal.getPicture();

        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

        holder.name.setText(titleString);
        holder.thumbnail.setImageBitmap(imageBitmap);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
