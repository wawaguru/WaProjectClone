package com.nowwego.hakwonga.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nowwego.hakwonga.R;

public class NavigationHomeFragment extends Fragment {
    private static final String FRAGMENT_NAME = "fragment_name";

    private ScheduleDailyFragment scheduleDailyFragment;
    private ScheduleWeeklyFragment scheduleWeeklyFragment;
    private ScheduleMonthlyFragment scheduleMonthlyFragment;

    private TabLayout tabLayoutSchedule;

    private String mName;

    public NavigationHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Fragment name.
     * @return A new instance of fragment NavigationHomeFragment.
     */
    public static NavigationHomeFragment newInstance(String name) {
        NavigationHomeFragment fragment = new NavigationHomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_nav_home, container, false);

        initScheduleFragments();

        bindViews(rootView);
        initScheduleTabLayout();

        return rootView;
    }

    private void bindViews(View root) {
        tabLayoutSchedule = root.findViewById(R.id.tabLayout_schedule);
    }

    private void initScheduleFragments() {
        scheduleDailyFragment = ScheduleDailyFragment.newInstance("DailySchedule");
        scheduleWeeklyFragment = ScheduleWeeklyFragment.newInstance("WeeklySchedule");
        scheduleMonthlyFragment = ScheduleMonthlyFragment.newInstance("MonthlySchedule");
    }

    private void initScheduleTabLayout() {
        tabLayoutSchedule.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                navigate(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // There is nothing to do here
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // There is nothing to do here
            }
        });

        tabLayoutSchedule.getTabAt(0).select();
    }

    private void navigate(int toPosition) {
        switch (toPosition) {
            case 0:
                loadFragment(scheduleDailyFragment);
                break;
            case 1:
                loadFragment(scheduleWeeklyFragment);
                break;
            case 2:
                loadFragment(scheduleMonthlyFragment);
                break;
            default:
                loadFragment(scheduleDailyFragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.schedule_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
