package com.design.senior.what2eat.ListViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.MealEditorActivity;
import com.design.senior.what2eat.MealViewerActivity;
import com.design.senior.what2eat.R;

import java.util.List;

/**
 * Created by KJ on 2/10/2018.
 */

public class MealEditorListAdapter extends RecyclerView.Adapter<MealEditorListAdapter.ViewHolder> {

    private List<Meal> dataSource;
    private Context context;
    private FragmentActivity activity;

    EditorFragmentRefresher refresher;

    public MealEditorListAdapter(FragmentActivity activity, List<Meal> dataSource, Context context) {
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
        protected Button editButton;
        protected Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.meal_editor_list_name);
            this.thumbnail = (ImageView) view.findViewById(R.id.meal_editor_list_thumbnail);
            this.viewButton = (Button) view.findViewById(R.id.meal_editor_list_view_meal);
            this.editButton = (Button) view.findViewById(R.id.meal_editor_list_edit_meal);
            this.deleteButton = (Button) view.findViewById(R.id.meal_editor_list_delete_meal);

            viewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Meal meal = dataSource.get(getAdapterPosition());

                    Intent intent = new Intent(context, MealViewerActivity.class);
                    intent.putExtra("meal", meal);
                    context.startActivity(intent);
                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Meal meal = dataSource.get(getAdapterPosition());

                    Intent intent = new Intent(context, MealEditorActivity.class);
                    intent.putExtra("meal", meal);
                    intent.putExtra("isNewMeal", false);
                    context.startActivity(intent);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Meal meal = dataSource.get(position);

                    try {
                        refresher = (EditorFragmentRefresher) activity;

                        dataSource.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dataSource.size());

                        refresher.deleteMealFromDatabase(meal);
                    } catch (ClassCastException e) {
                        throw new ClassCastException(activity.toString() + "must implement EditorFragmentRefresher");
                    }
                }
            });
        }
    }

    public interface EditorFragmentRefresher {
        void deleteMealFromDatabase(Meal meal);
    }

    @Override
    public MealEditorListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
