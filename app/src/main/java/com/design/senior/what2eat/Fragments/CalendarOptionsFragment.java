package com.design.senior.what2eat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.design.senior.what2eat.R;

/**
 * Created by KJ on 3/24/2018.
 */

public class CalendarOptionsFragment extends Fragment {

    private TextView helloText;

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
        helloText = (TextView) view.findViewById(R.id.testText);

        return view;
    }
}
