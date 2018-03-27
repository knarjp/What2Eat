package com.design.senior.what2eat.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;
import com.design.senior.what2eat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ on 3/24/2018.
 */

public class CalendarOptionsFragment extends Fragment {

    private OptionsViewToParentActivityCommunicator communicator;

    private TextView allergiesText;
    private TextView dietText;

    private CheckBox milkCheck;
    private CheckBox eggsCheck;
    private CheckBox fishCheck;
    private CheckBox shellfishCheck;
    private CheckBox treenutsCheck;
    private CheckBox peanutsCheck;
    private CheckBox wheatCheck;
    private CheckBox soyCheck;

    private RadioGroup dietsRadioGroup;

    public CalendarOptionsFragment() {
        // Required empty public constructor
    }

    public static CalendarOptionsFragment newInstance() {
        // make an empty fragment and return it
        CalendarOptionsFragment fragment = new CalendarOptionsFragment();

        return fragment;
    }

    // TODO: add button and interface method for deciding if we want to generate custom meals or not

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_options, container, false);

        // assign view fields to xml IDs
        allergiesText = (TextView) view.findViewById(R.id.options_allergies_header);
        dietText = (TextView) view.findViewById(R.id.options_diets_header);

        milkCheck = (CheckBox) view.findViewById(R.id.allergies_milk_checkbox);
        eggsCheck = (CheckBox) view.findViewById(R.id.allergies_eggs_checkbox);
        fishCheck = (CheckBox) view.findViewById(R.id.allergies_fish_checkbox);
        shellfishCheck = (CheckBox) view.findViewById(R.id.allergies_shellfish_checkbox);
        treenutsCheck = (CheckBox) view.findViewById(R.id.allergies_treenuts_checkbox);
        peanutsCheck = (CheckBox) view.findViewById(R.id.allergies_peanuts_checkbox);
        wheatCheck = (CheckBox) view.findViewById(R.id.allergies_wheat_checkbox);
        soyCheck = (CheckBox) view.findViewById(R.id.allergies_soy_checkbox);

        dietsRadioGroup = (RadioGroup) view.findViewById(R.id.options_radio_group);

        milkCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(milkCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.MILK);
                } else {
                    communicator.removeAllergy(AllergyType.MILK);
                }
            }
        });

        eggsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(eggsCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.EGGS);
                } else {
                    communicator.removeAllergy(AllergyType.EGGS);
                }
            }
        });

        fishCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fishCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.FISH);
                } else {
                    communicator.removeAllergy(AllergyType.FISH);
                }
            }
        });

        shellfishCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shellfishCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.SHELLFISH);
                } else {
                    communicator.removeAllergy(AllergyType.SHELLFISH);
                }
            }
        });

        treenutsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(treenutsCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.TREENUTS);
                } else {
                    communicator.removeAllergy(AllergyType.TREENUTS);
                }
            }
        });

        peanutsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(peanutsCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.PEANUTS);
                } else {
                    communicator.removeAllergy(AllergyType.PEANUTS);
                }
            }
        });

        wheatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(wheatCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.WHEAT);
                } else {
                    communicator.removeAllergy(AllergyType.WHEAT);
                }
            }
        });

        soyCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(soyCheck.isChecked()) {
                    communicator.addAllergy(AllergyType.SOY);
                } else {
                    communicator.removeAllergy(AllergyType.SOY);
                }
            }
        });

        dietsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.options_none_radio:
                        List<DietType> allowedDiets_none = new ArrayList<>();

                        allowedDiets_none.add(DietType.VEGAN);
                        allowedDiets_none.add(DietType.VEGETARIAN);
                        allowedDiets_none.add(DietType.PESCETARIAN);
                        allowedDiets_none.add(DietType.PALEO);

                        communicator.setDietType(allowedDiets_none);
                        break;
                    case R.id.options_vegan_radio:
                        List<DietType> allowedDiets_vegan = new ArrayList<>();

                        allowedDiets_vegan.add(DietType.VEGAN);

                        communicator.setDietType(allowedDiets_vegan);
                        break;
                    case R.id.options_vegetarian_radio:
                        List<DietType> allowedDiets_vegetarian = new ArrayList<>();

                        allowedDiets_vegetarian.add(DietType.VEGAN);
                        allowedDiets_vegetarian.add(DietType.VEGETARIAN);

                        communicator.setDietType(allowedDiets_vegetarian);
                        break;
                    case R.id.options_pescetarian_radio:
                        List<DietType> allowedDiets_pescetarian = new ArrayList<>();

                        allowedDiets_pescetarian.add(DietType.VEGAN);
                        allowedDiets_pescetarian.add(DietType.VEGETARIAN);
                        allowedDiets_pescetarian.add(DietType.PESCETARIAN);

                        communicator.setDietType(allowedDiets_pescetarian);
                        break;
                    case R.id.options_paleo_radio:
                        List<DietType> allowedDiets_paleo = new ArrayList<>();

                        allowedDiets_paleo.add(DietType.PALEO);

                        communicator.setDietType(allowedDiets_paleo);
                        break;
                }
            }
        });

        return view;
    }

    public interface OptionsViewToParentActivityCommunicator {
        void setDietType(List<DietType> allowedDiets);
        void addAllergy(AllergyType allergyType);
        void removeAllergy(AllergyType allergyType);
    }

    @Override
    public void onAttach(Context context) { // required for android API versions on or after 23
        super.onAttach(context);

        Activity activity;

        if(context instanceof Activity) { // TODO: oh god this is gross figure out how to get around this
            activity = (Activity) context;

            try {
                communicator = (OptionsViewToParentActivityCommunicator) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + "must implement OptionsViewToParentActivityCommunicator");
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) { // required for android API versions before 23
        super.onAttach(activity);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                communicator = (OptionsViewToParentActivityCommunicator) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + "must implement OptionsViewToParentActivityCommunicator");
            }
        }
    }
}
