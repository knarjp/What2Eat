package com.design.senior.what2eat.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MealViewerFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView ingredients;
    private TextView recipe;
    private TextView allergies;
    private TextView calories;

    // the fragment initialization parameters
    private static final String IMAGE_ARG = "image";
    private static final String NAME_ARG = "name";
    private static final String INGREDIENTS_ARG = "ingredients";
    private static final String RECIPE_ARG = "recipe";
    private static final String ALLERGIES_ARG = "allergies";
    private static final String CALORIES_ARG = "calories";
    private static final String MEAL_ARG = "meal";

    public MealViewerFragment() {
        // Required empty public constructor
    }

    public static MealViewerFragment newInstance(Meal meal) {
        // make an empty fragment
        MealViewerFragment fragment = new MealViewerFragment();
        // TODO: need to revert to old system, breaks if data is too large
        // make a bundle to set
        Bundle args = new Bundle();

        // set the variables of the fragment to be the meal
        args.putParcelable(MEAL_ARG, meal);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_viewer, container, false);

        // assign view fields to xml IDs
        name = (TextView) view.findViewById(R.id.meal_viewer_name);
        image = (ImageView) view.findViewById(R.id.meal_viewer_image);
        ingredients = (TextView) view.findViewById(R.id.meal_viewer_ingredients_content);
        recipe = (TextView) view.findViewById(R.id.meal_viewer_recipe_content);
        calories = (TextView) view.findViewById(R.id.meal_viewer_calories_content);
        allergies = (TextView) view.findViewById(R.id.meal_viewer_allergies_content);

        // get data for view fields
        if (getArguments() != null) {

            Meal meal = getArguments().getParcelable(MEAL_ARG);

            if(meal != null) {
                name.setText(meal.getName());
                ingredients.setText(meal.getIngredients());
                recipe.setText(meal.getRecipe());
                calories.setText(String.valueOf(meal.getCaloricAmount()));

                List<AllergyType> allergiesList = meal.getAllergiesEnum();
                String allergiesString = "";
                for (AllergyType allergy : allergiesList) {
                    allergiesString += allergy.toString() + ", ";
                }

                if (allergies.length() > 2) {
                    allergies.setText(allergiesString.substring(0, allergiesString.length() - 2));
                } else {
                    allergies.setText(allergiesString);
                }

                try {
                    byte[] imageByteArray = meal.getPicture();
                    Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

                    image.setImageBitmap(imageBitmap);
                } catch (Exception e) {
                    // do nothing if image cannot be loaded (keep default image)
                }
            }
        }

        return view;
    }
}
