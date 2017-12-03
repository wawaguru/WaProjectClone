package com.nowwego.hakwonga.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nowwego.hakwonga.R;
import com.nowwego.hakwonga.data.ScheduleData;

import java.util.ArrayList;


public class ScheduleDataAdapter  extends RecyclerView.Adapter<ScheduleDataAdapter.ViewHolderMorePostItem> {
    private ArrayList<ScheduleData> mScheduleList;

    public ScheduleDataAdapter(ArrayList<ScheduleData> scheduleList) {
        this.mScheduleList = scheduleList;
    }

    @Override
    public ViewHolderMorePostItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_schedule_daily, parent, false);
        return new ViewHolderMorePostItem(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderMorePostItem holder, final int position) {
        final ScheduleData schedule = mScheduleList.get(position);

        setAdapterData(holder, schedule);
        setIndicator(holder.thisView.getContext(), schedule.getType(), holder.viewIndicator);
        setClickListener(holder, schedule);
    }

    @Override
    public int getItemCount() {
        return mScheduleList.size();
    }

    public static class ViewHolderMorePostItem extends RecyclerView.ViewHolder {
        private View thisView;
        private View viewIndicator;
        private TextView textViewType;
        private TextView textViewTime;
        private TextView textViewDescription;
        private TextView textViewDetails1;
        private TextView textViewDetails2;

        private ViewHolderMorePostItem(final View itemView) {
            super(itemView);

            thisView = itemView;
            viewIndicator = itemView.findViewById(R.id.view_schedule_indicator);
            textViewType = itemView.findViewById(R.id.textView_schedule_type);
            textViewTime = itemView.findViewById(R.id.textView_schedule_time);
            textViewDescription = itemView.findViewById(R.id.textView_schedule_description);
            textViewDetails1 = itemView.findViewById(R.id.textView_schedule_detail_1);
            textViewDetails2 = itemView.findViewById(R.id.textView_schedule_detail_2);
        }
    }

    private void setIndicator(Context ctx, String type, View indicator) {
        // TODO: Set color according to data set

        // TODO: Remove this later
        indicator.setBackground(ctx.getResources().getDrawable(R.drawable.card_schedule_indicator_active));
    }

    private void setAdapterData(ViewHolderMorePostItem holder, ScheduleData schedule) {
        if (!schedule.getDate().toString().isEmpty()) {
            holder.textViewTime.setText(schedule.getDate().toString());
        }

        if (!schedule.getType().isEmpty()) {
            holder.textViewType.setText(schedule.getType());
        }

        if (!schedule.getDescription().isEmpty()) {
            holder.textViewDescription.setText(schedule.getDescription());
        }

        if (!schedule.getDetails().isEmpty()) {
            holder.textViewDetails1.setText(schedule.getDetails());
        }

        if (!schedule.getExtras().isEmpty()) {
            holder.textViewDetails2.setText(schedule.getExtras());
        }
    }

    private void setClickListener(final ViewHolderMorePostItem holder, final ScheduleData schedule) {
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When this item clicked
                Context parentContext = holder.thisView.getContext();
            }
        });
    }
}
