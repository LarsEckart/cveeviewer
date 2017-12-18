package ee.tublipoiss.cveeviewer.presentation.joblist;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import ee.tublipoiss.cveeviewer.R;
import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.JobAdParser;
import ee.tublipoiss.cveeviewer.data.JobAdsAdapter;
import ee.tublipoiss.cveeviewer.presentation.fragments.ContentFetcher;
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
    private Handler handler = new Handler();

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

        prepareUI();

        if ((savedInstanceState != null) && savedInstanceState.containsKey(BUNDLE_JOB_LIST_KEY)) {
            Timber.d("restoring savedInstanceState");
            adapter.clear();
            ArrayList<String> jsonJobList = savedInstanceState.getStringArrayList(BUNDLE_JOB_LIST_KEY);
            assert jsonJobList != null;
            for (String jsonString : jsonJobList) {
                adapter.add(JobAd.fromJSON(jsonString));
            }
            //adapter.notifyDataSetChanged();
        } else {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    loadJobAds();
                }
            });
            t.start();
        }
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

    private void prepareUI() {
        FragmentManager fm = getFragmentManager();
        ListFragment jobListFragment = (JobListFragment) fm.findFragmentById(R.id.JobListFragment);

        adapter = new JobAdsAdapter(getActivity(), R.layout.row, jobAds);
        jobListFragment.setListAdapter(adapter);
    }

    private void loadJobAds() {

        String url =
                "https://www.cv.ee/job-ads/all?type=rss&Tegvk=information-technology&Location=tartu-county&Town=tartu-county-tartu&Language=inglise";

        ConnectivityManager connectivity =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        // TODO got force error without any internet connection
        boolean isConnected = ((activeNetwork != null) && (activeNetwork.isConnected()));
        if (!isConnected) {
            // TODO implement DialogFragment to redirect users to settings or cancel
            Timber.d("no internet");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Timber.d("loading jobs from the internet");

            ContentFetcher ct = new ContentFetcher();
            ct.setUrl(url);
            final List<JobAd> jobs = new JobAdParser().parse(ct.fetch());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter.clear();
                    for (JobAd job : jobs) {
                        adapter.add(job);
                    }
                    //adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Job ads loaded!", Toast.LENGTH_SHORT).show();
                }
            });
        }
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