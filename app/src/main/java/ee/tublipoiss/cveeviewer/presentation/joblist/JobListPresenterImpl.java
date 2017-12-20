package ee.tublipoiss.cveeviewer.presentation.joblist;

import java.util.List;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.JobAdRepository;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;

public class JobListPresenterImpl implements JobListPresenter, LoadJobAdsCallback {

    private final JobListView view;
    private JobAdRepository repository;

    @Inject
    public JobListPresenterImpl(JobListView view, JobAdRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {
        view.showLoading();
        repository.getJobAds(this);
    }

    @Override
    public void onJobAdsLoaded(List<JobAd> jobAds) {
        view.showJobAds(jobAds);
    }
}
