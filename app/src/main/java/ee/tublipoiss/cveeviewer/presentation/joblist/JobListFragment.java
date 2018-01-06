package ee.tublipoiss.cveeviewer.presentation.joblist;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import ee.tublipoiss.cveeviewer.R;
import ee.tublipoiss.cveeviewer.data.JobAd;
import timber.log.Timber;

/**
 * Created by lars on 27.07.13.
 */
public class JobListFragment extends ListFragment implements JobListView {

    private static final String BUNDLE_JOB_LIST_KEY = "jobListKey";

    @Inject
    JobListPresenter presenter;

    private List<JobAd> jobAds = new ArrayList<JobAd>();
    private JobAdsAdapter adapter;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("onActivityCreated");
        this.presenter.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.d("onSaveInstanceState");
        ArrayList<String> jobStringList = new ArrayList<String>();
        for (JobAd job : jobAds) {
            jobStringList.add(job.toJSON().toString());
        }
        outState.putStringArrayList(BUNDLE_JOB_LIST_KEY, jobStringList);
    }

    @Override
    public void showLoading() {
        this.setListShown(false);
    }

    @Override
    public void showJobAds(final List<JobAd> jobAds) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setListShown(true);
                adapter = new JobAdsAdapter(getActivity(), R.layout.row, jobAds);
                FragmentManager fm = getFragmentManager();
                ListFragment jobListFragment = (JobListFragment) fm.findFragmentById(R.id.JobListFragment);
                jobListFragment.setListAdapter(adapter);
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Timber.d("onListItemClick");

        String url = jobAds.get(position).getLink().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}