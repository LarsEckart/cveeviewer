package ee.tublipoiss.cveeviewer.presentation.joblist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ee.tublipoiss.cveeviewer.R;
import ee.tublipoiss.cveeviewer.data.JobAd;

public class JobAdsAdapter extends ArrayAdapter<JobAd> {

    private List<JobAd> jobAds = new ArrayList<JobAd>();

    private int layoutId;

    public JobAdsAdapter(Context context, int layoutId, List<JobAd> items) {
        super(context, layoutId, items);
        jobAds = items;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return jobAds.size();
    }

    @Override
    public JobAd getItem(int index) {
        return jobAds.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row = inflater.inflate(layoutId, parent, false);
        }

        ViewHolder holder = (ViewHolder) row.getTag();
        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        JobAd jobAd = jobAds.get(position);

        holder.empTextView.setText(jobAd.getEmployer());
        holder.jobTextView.setText(jobAd.getJobTitle());
        holder.pubDateTextView.setText(jobAd.getDate());
        holder.deadlineTextView.setText(jobAd.getDeadline());

        LinearLayout ll = (LinearLayout) row.findViewById(R.id.jobAdLinearLayout);

        if (position % 2 == 0) {
            ll.setBackgroundResource(R.color.cv_background_yellow);
        } else {
            ll.setBackgroundResource(R.color.white);
        }
        return row;
    }

    static class ViewHolder {
        TextView empTextView;
        TextView jobTextView;
        TextView pubDateTextView;
        TextView deadlineTextView;

        ViewHolder(View row) {
            this.empTextView = (TextView) row.findViewById(R.id.employerView);
            this.jobTextView = (TextView) row.findViewById(R.id.jobView);
            this.pubDateTextView = (TextView) row.findViewById(R.id.dateView);
            this.deadlineTextView = (TextView) row.findViewById(R.id.deadlineView);
        }
    }
}