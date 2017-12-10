package com.nowwego.hakwonga.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.nowwego.hakwonga.R;
import com.nowwego.hakwonga.adapters.ScheduleDataAdapter;
import com.nowwego.hakwonga.data.ScheduleData;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleMonthlyFragment extends Fragment {
    private static final String FRAGMENT_NAME = "fragment_name";

    private String mName;
    private RecyclerView recyclerViewScheduleDaily;
    private CalendarView calendarView;

    private ScheduleDataAdapter mScheduleDataAdapter;
    private ArrayList<ScheduleData> mScheduleDataList = new ArrayList<>();

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

        mScheduleDataAdapter = new ScheduleDataAdapter(mScheduleDataList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_schedule_monthly, container, false);
        // Create a calendar view with the current date
        calendarView = new CalendarView(getContext(), Calendar.getInstance());
        // Add calendar view to the root layout
        LinearLayout ll = root.findViewById(R.id.linearLayout_monthly_schedule);
        ll.addView(calendarView.getView());

        bindViews(root);
        initViews();

        return root;
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
        recyclerViewScheduleDaily = root.findViewById(R.id.recyclerView_schedule_daily);
    }

    private void initViews() {
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mockSchedule();
            }
        });
        initRecyclerViewScheduleDaily();
    }

    private void initRecyclerViewScheduleDaily() {
        recyclerViewScheduleDaily.setAdapter(mScheduleDataAdapter);
        recyclerViewScheduleDaily.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
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
