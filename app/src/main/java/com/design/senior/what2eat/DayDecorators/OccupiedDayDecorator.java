package com.design.senior.what2eat.DayDecorators;

// Material Calendar View - Copyright (c) 2017 Prolific Interactive - see LICENSE.md for licensing credits
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by KJ on 2/6/2018.
 */

public class OccupiedDayDecorator implements DayViewDecorator {
    private final int color;
    private final List<Date> dates;

    public OccupiedDayDecorator(int color, List<Date> dates) {
        this.color = color;
        this.dates = dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Calendar cal = Calendar.getInstance();

        for(Date date: dates) {
            cal.setTime(date);
            int dateYear = cal.get(Calendar.YEAR);
            int dateMonth = cal.get(Calendar.MONTH);
            int dateDay = cal.get(Calendar.DAY_OF_MONTH);

            if(dateYear == day.getYear() && dateMonth == day.getMonth() && dateDay == day.getDay()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }
}
