package com.nowwego.hakwonga.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nowwego.hakwonga.R;

import java.util.ArrayList;
import java.util.Calendar;


public class CalendarView extends View {

    private Context mContext;
    private ViewGroup mView;
    private ArrayList<CalendarDayView> mDays = new ArrayList<>();
    private ArrayList<CalendarDayView> mDaysOfMonth = new ArrayList<>();
    private Calendar mCalendar;


    public CalendarView(Context context) {
        super(context);
        mContext = context;

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
        for (int i = 0; i < 5; i ++) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < 7; j++) {
                CalendarDayView day = new CalendarDayView(mContext);
                linearLayout.addView(day.getView());
                mDays.add(day);
            }
            // There 2 views before day views in calendar
            mView.addView(linearLayout, i + 2);
        }
    }

    private void updateCalendar() {
        int numberOfDays = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDay = mCalendar.getActualMinimum(Calendar.DAY_OF_WEEK);
        int dayIndex = 1;

        Log.e("CalendarView", "Firstday: " + firstDay);

        // Set the first day of month
        mDays.get(firstDay).setText(Integer.toString(dayIndex++));
        mDaysOfMonth.add(mDays.get(firstDay));

        // Set the rest of month
        for (int i = firstDay; i < numberOfDays + firstDay ; i++) {
            mDays.get(i).setText(Integer.toString(dayIndex++));
            mDaysOfMonth.add(mDays.get(i));
        }
    }
}
