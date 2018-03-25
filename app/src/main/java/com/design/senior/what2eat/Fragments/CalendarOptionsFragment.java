package com.design.senior.what2eat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.design.senior.what2eat.R;

/**
 * Created by KJ on 3/24/2018.
 */

public class CalendarOptionsFragment extends Fragment {

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

    private RadioGroup radioGroup;

    public CalendarOptionsFragment() {
        // Required empty public constructor
    }

    public static CalendarOptionsFragment newInstance() {
        // make an empty fragment and return it
        CalendarOptionsFragment fragment = new CalendarOptionsFragment();

        return fragment;
    }

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

        radioGroup = (RadioGroup) view.findViewById(R.id.options_radio_group);

        // TODO: set onclick listeners

        return view;
    }
}
