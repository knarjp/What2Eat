package com.design.senior.what2eat.ListViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Entities.MealEntryJoin;
import com.design.senior.what2eat.MealViewerActivity;
import com.design.senior.what2eat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ on 3/27/2018.
 */

public class GeneratedMealListAdapter extends RecyclerView.Adapter<GeneratedMealListAdapter.ViewHolder> {

    private List<Meal> dataSource;
    private List<MealEntryJoin> entries;
    private Context context;
    private FragmentActivity activity;

    private FragmentRefresher fragmentRefresher;

    public GeneratedMealListAdapter(FragmentActivity activity, List<Meal> dataSource, List<MealEntryJoin> entries, Context context) {
        this.activity = activity;
        this.dataSource = dataSource;
        this.entries = entries;
        this.context = context;
    }

    public interface ClickListener {
        void onPositionClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected ImageView thumbnail;
        protected Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.meal_editor_list_name);
            this.thumbnail = (ImageView) view.findViewById(R.id.meal_editor_list_thumbnail);
            this.deleteButton = (Button) view.findViewById(R.id.meal_editor_list_delete_meal);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meal meal = dataSource.get(getAdapterPosition());

                    Intent intent = new Intent(context, MealViewerActivity.class);
                    intent.putExtra("meal", meal);
                    context.startActivity(intent);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteButton.setEnabled(false);

                    int position = getAdapterPosition();

                    try {
                        fragmentRefresher = (FragmentRefresher) activity;

                        int mealID = dataSource.get(position).getMealID();

                        MealEntryJoin entryToDelete;

                        List<MealEntryJoin> entriesCopy = new ArrayList<>(entries);

                        for(MealEntryJoin join : entriesCopy) {
                            if(join.getMeal() == mealID) {
                                entryToDelete = join;

                                fragmentRefresher.deleteGeneratedEntryFromDatabase(entryToDelete);

                                dataSource.remove(position);
                                entries.remove(entryToDelete);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, dataSource.size());
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, entries.size());

                                fragmentRefresher.refreshView();
                                break;
                            }
                        }
                    } catch (ClassCastException e) {
                        throw new ClassCastException(activity.toString() + "must implement FragmentRefresher");
                    }

                    deleteButton.setEnabled(true);
                }
            });
        }
    }

    public interface FragmentRefresher {
        void deleteGeneratedEntryFromDatabase(MealEntryJoin entry);
        void refreshView();
    }

    @Override
    public GeneratedMealListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generated_listview_layout, parent, false);

        GeneratedMealListAdapter.ViewHolder viewHolder = new GeneratedMealListAdapter.ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GeneratedMealListAdapter.ViewHolder holder, int position) {
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
