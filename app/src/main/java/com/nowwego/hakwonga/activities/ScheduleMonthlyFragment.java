package com.nowwego.hakwonga.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nowwego.hakwonga.R;

import java.util.Calendar;

public class ScheduleMonthlyFragment extends Fragment {
    private static final String FRAGMENT_NAME = "fragment_name";

    private String mName;

    public ScheduleMonthlyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Name of this fragment.
     * @return A new instance of fragment ScheduleMonthlyFragment.
     */
    public static ScheduleMonthlyFragment newInstance(String name) {
        ScheduleMonthlyFragment fragment = new ScheduleMonthlyFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(FRAGMENT_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_schedule_monthly, container, false);
        CalendarView calendarView = new CalendarView(getContext());
        calendarView.setDate(Calendar.getInstance());

        LinearLayout ll = root.findViewById(R.id.linearLayout_monthly_schedule);
        ll.addView(calendarView.getView());

        return root;
    }
}
