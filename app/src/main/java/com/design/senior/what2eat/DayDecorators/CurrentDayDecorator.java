package com.design.senior.what2eat.DayDecorators;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see LICENSE.md for licensing credits
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

/**
 * Created by KJ on 2/5/2018.
 */

public class CurrentDayDecorator implements DayViewDecorator {

    private final int color;
    private final CalendarDay today;

    public CurrentDayDecorator(int color, CalendarDay today) {
        this.color = color;
        this.today = today;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(today);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }
}
