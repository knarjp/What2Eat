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

import java.util.ArrayList;
import java.util.List;

public class MealViewerFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView ingredients;
    private TextView recipe;
    private TextView allergies;

    // the fragment initialization parameters
    private static final String IMAGE_ARG = "image";
    private static final String NAME_ARG = "name";
    private static final String INGREDIENTS_ARG = "ingredients";
    private static final String RECIPE_ARG = "recipe";
    private static final String ALLERGIES_ARG = "allergies";

    public MealViewerFragment() {
        // Required empty public constructor
    }

    public static MealViewerFragment newInstance(Meal meal) {
        // make an empty fragment
        MealViewerFragment fragment = new MealViewerFragment();

        // make a bundle to set
        Bundle args = new Bundle();

        // set the variables of the fragment (its picture, recipe, etc.) to meal's attributes
        args.putByteArray(IMAGE_ARG, meal.getPicture());
        args.putString(NAME_ARG, meal.getName());
        args.putString(INGREDIENTS_ARG, meal.getIngredients());
        args.putString(RECIPE_ARG, meal.getRecipe());

        // get all the allergies strings from the meal's allergies list
        ArrayList<String> allergiesStrings = new ArrayList<>();
        List<AllergyType> allergies = meal.getAllergiesEnum();

        for(AllergyType allergy : allergies) {
            allergiesStrings.add(allergy.toString());
        }

        args.putStringArrayList(ALLERGIES_ARG, allergiesStrings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_viewer, container, false);

        // assign view fields to xml IDs
        name = (TextView)view.findViewById(R.id.meal_editor_list_name);
        image = (ImageView) view.findViewById(R.id.meal_editor_list_thumbnail);
        ingredients = (TextView) view.findViewById(R.id.meal_viewer_ingredients_content);
        recipe = (TextView) view.findViewById(R.id.meal_viewer_recipe_content);
        allergies = (TextView) view.findViewById(R.id.meal_viewer_allergies_content);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name.setText(savedInstanceState.getString(NAME_ARG));
            ingredients.setText(savedInstanceState.getString(INGREDIENTS_ARG));
            recipe.setText(savedInstanceState.getString(RECIPE_ARG));
            allergies.setText(savedInstanceState.getString(ALLERGIES_ARG));

            try {
                byte[] imageByteArray = savedInstanceState.getByteArray(IMAGE_ARG);
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

                image.setImageBitmap(imageBitmap);
            } catch(Exception e) {
                // do nothing if image cannot be loaded (keep default image)
            }
        }
    }
}
