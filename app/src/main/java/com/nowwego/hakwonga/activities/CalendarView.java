package com.nowwego.hakwonga.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nowwego.hakwonga.R;
import com.nowwego.hakwonga.Utils;

import java.util.ArrayList;
import java.util.Calendar;


public class CalendarView extends View {

    private Context mContext;
    private ViewGroup mView;
    private ArrayList<CalendarDayView> mDays = new ArrayList<>();
    private ArrayList<CalendarDayView> mDaysOfMonth = new ArrayList<>();
    private Calendar mCalendar;


    public CalendarView(Context context, Calendar date) {
        super(context);
        mContext = context;
        mCalendar = date;

        inflateView();
        initView();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        inflateView();
        initView();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        inflateView();
        initView();
    }

    public void setOnClickListener(OnClickListener listener) {
        for (int i = 0; i < mDays.size(); i++) {
            if (false) {
                break;
            }
            mDays.get(i).setOnClickListener(listener);
        }
    }

    // TODO: before setting the date, calendar view must be cleared
    public void setDate(Calendar calendar) {
        mCalendar = calendar;
        updateCalendar();
    }

    public ViewGroup getView() {
        return mView;
    }

    private void inflateView() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        mView = (ViewGroup) layoutInflater.inflate(R.layout.view_calendar, null);
    }

    private void initView() {
        createDaysOfMonth();
        updateCalendar();
    }

    private void createDaysOfMonth() {
        int numberOfWeeks = mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        for (int i = 0; i < numberOfWeeks; i ++) {
            LinearLayout week = createWeek();

            for (int j = 0; j < 7; j++) {
                CalendarDayView day = new CalendarDayView(mContext);
                // Set color of days of weekend
                if (j % 7 == 0 || j % 7 == 6) {
                    day.setTextColor(ContextCompat.getColor(getContext(), R.color.cool_grey));
                }
                week.addView(day.getView());
                mDays.add(day);
            }
            // There are 2 views before day of month view in calendar
            // (which are days of top border and week layout)
            // therefore add this layout at index i + 2
            mView.addView(week, i + 2);
        }
    }

    private void updateCalendar() {
        int numberOfDays = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDay = Utils.getFirstDayOfMonth(mCalendar.getTime());
        int dayIndex = 1;

        // Set the first day of month
        // Days of week starting from index 1 not 0
        // ex: sunday => 1, saturday => 7
        mDays.get(firstDay - 1).setText(Integer.toString(dayIndex++));
        mDaysOfMonth.add(mDays.get(firstDay));

        // Set the rest of month
        for (int i = firstDay; i < numberOfDays + firstDay - 1; i++) {
            mDays.get(i).setText(Integer.toString(dayIndex++));
            mDaysOfMonth.add(mDays.get(i));
        }
    }

    private LinearLayout createWeek() {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        return linearLayout;
    }
}
