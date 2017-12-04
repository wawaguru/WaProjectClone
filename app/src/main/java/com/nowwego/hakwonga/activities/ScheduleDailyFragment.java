package com.nowwego.hakwonga.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nowwego.hakwonga.R;
import com.nowwego.hakwonga.adapters.ScheduleDataAdapter;
import com.nowwego.hakwonga.data.ScheduleData;

import java.util.ArrayList;

public class ScheduleDailyFragment extends Fragment {
    private static final String FRAGMENT_NAME = "fragment_name";

    private TabLayout tabLayoutCalendar;
    private RecyclerView recyclerViewScheduleDaily;

    private String mName;
    private ScheduleDataAdapter mScheduleDataAdapter;
    private ArrayList<ScheduleData> mScheduleDataList = new ArrayList<>();


    public ScheduleDailyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Name of the fragment.
     * @return A new instance of fragment ScheduleDailyFragment.
     */
    public static ScheduleDailyFragment newInstance(String name) {
        ScheduleDailyFragment fragment = new ScheduleDailyFragment();
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

        mScheduleDataAdapter = new ScheduleDataAdapter(mScheduleDataList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_schedule_daily, container, false);

        bindViews(rootView);
        initViews();

        return rootView;
    }

    public void setDataSet(ArrayList<ScheduleData> dataSet) {
        if (!dataSet.isEmpty()) {
            mScheduleDataList.clear();
            mScheduleDataList.addAll(dataSet);
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        if (mScheduleDataAdapter != null) {
            mScheduleDataAdapter.notifyDataSetChanged();
        }
    }

    private void bindViews(View root) {
        tabLayoutCalendar = root.findViewById(R.id.tabLayout_calendar);
        recyclerViewScheduleDaily = root.findViewById(R.id.recyclerView_schedule_daily);
    }

    private void initViews() {
        initCalendarTabLayout();
        initRecyclerViewScheduleDaily();
    }

    private void initRecyclerViewScheduleDaily() {
        recyclerViewScheduleDaily.setAdapter(mScheduleDataAdapter);
        recyclerViewScheduleDaily.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void initCalendarTabLayout() {
        initTabItems();

        // TODO: Set listeners

        tabLayoutCalendar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // TODO: Set data for each day
                // TODO: Remove this later
                mockSchedule();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTabItems() {
        for (int i = 0; i < tabLayoutCalendar.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayoutCalendar.getTabAt(i);
            tab.setCustomView(customizeTabItem(i));
        }
    }

    private View customizeTabItem(int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View tabView = layoutInflater.inflate(R.layout.content_calender_tab, null, false);
        TextView dayOfWeek = tabView.findViewById(R.id.textView_day_of_week);
        TextView dayOfMonth = tabView.findViewById(R.id.textView_day_of_month);
        dayOfWeek.setText(getDayOfWeek(position));
        dayOfMonth.setText(getDayOfMonth(position));

        // We rotate the view up-side-down because
        // in the parent TabLayout xml tag we set
        // the rotation 180 in order to make
        // indicator appear on top instead of bottom.
        tabView.setRotationX(180);

        return tabView;
    }

    private String getDayOfWeek(int index) {
        switch (index) {
            case 0:
                return getString(R.string.tab_name_monday);
            case 1:
                return getString(R.string.tab_name_tuesday);
            case 2:
                return getString(R.string.tab_name_wednesday);
            case 3:
                return getString(R.string.tab_name_thursday);
            case 4:
                return getString(R.string.tab_name_friday);
            case 5:
                return getString(R.string.tab_name_saturday);
            case 6:
                return getString(R.string.tab_name_sunday);
            default:
                return getString(R.string.tab_name_monday);
        }
    }

    // TODO: Get the data from model
    private String getDayOfMonth(int index) {
        return Integer.toString(15 + index);
    }

    // TODO: Connect to user action
    private void refreshRideList() {
        getSchedule();
        notifyDataSetChanged();
    }

    // TODO: Implement
    private void getSchedule() {

    }

    private void mockSchedule() {
        ArrayList<ScheduleData> mockSchedule = new ArrayList<>();
        Gson gson = new Gson();
        String mockJsonData1 = "{\"type\":\"학교일정\", " +
                "\"date\":123456, \"description\":\"문현고등학교\", " +
                "\"details\":\"2학년 1반\", \"extras\":\"물리 / 영어 / 미적분 / 한국지리 / 윤리 / 중국어\"}";
        String mockJsonData2 = "{\"type\":\"학원일정\", " +
                "\"date\":123456, \"description\":\"이은재어학원\", " +
                "\"details\":\"2학기 내신대비\", \"extras\":\"강의실 1B / 김재인 선생님\"}";
        String mockJsonData3 = "{\"type\":\"학원일정\", " +
                "\"date\":123456, \"description\":\"카이스트수학학원\", " +
                "\"details\":\"미적분 특강\", \"extras\":\"강의실 201 / 정승모 선생님\"}";
        String mockJsonData4 = "{\"type\":\"자기주도\", " +
                "\"date\":123456, \"description\":\"영어 3과 복습\", " +
                "\"details\":\"\", \"extras\":\"\"}";
        ScheduleData mock1 = gson.fromJson(mockJsonData1, ScheduleData.class);
        ScheduleData mock2 = gson.fromJson(mockJsonData2, ScheduleData.class);
        ScheduleData mock3 = gson.fromJson(mockJsonData3, ScheduleData.class);
        ScheduleData mock4 = gson.fromJson(mockJsonData4, ScheduleData.class);

        mockSchedule.add(mock1);
        mockSchedule.add(mock2);
        mockSchedule.add(mock3);
        mockSchedule.add(mock4);

        setDataSet(mockSchedule);
    }
}
