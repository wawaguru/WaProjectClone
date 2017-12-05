package com.nowwego.hakwonga.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nowwego.hakwonga.R;
import com.nowwego.hakwonga.Utils;

public class CalendarDayView extends View {

    private Context mContext;
    private View mView;
    private View mIndicator;
    private TextView mDay;

    public CalendarDayView(Context context) {
        super(context);
        mContext = context;

        inflateView();
        initView();
    }

    public CalendarDayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        inflateView();
        initView();
    }

    public CalendarDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        inflateView();
        initView();
    }

    public ViewGroup getView() {
        return (ViewGroup) mView;
    }

    public void setText(CharSequence text) {
        mDay.setText(text);
    }

    public void setTextColor(int color) {
        mDay.setTextColor(color);
    }

    public void setIndicator(boolean set) {
        mIndicator.setVisibility(set ? VISIBLE : GONE);
    }

    public void setOnClickListener(final OnClickListener listener) {
        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
                listener.onClick(v);
            }
        });
    }

    private void inflateView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        mView = layoutInflater.inflate(R.layout.view_day_of_month, null);
    }

    private void initView() {
        // Set layout params of day view
        ((LinearLayout) mView).setOrientation(LinearLayout.VERTICAL);
        mView.setLayoutParams(new LinearLayout.LayoutParams(
                0, Utils.dpToPx(getContext(),42), 1.0f));

        // Find views on day view
        mDay = mView.findViewById(R.id.day);
        mIndicator = mView.findViewById(R.id.indicator);
    }
}
