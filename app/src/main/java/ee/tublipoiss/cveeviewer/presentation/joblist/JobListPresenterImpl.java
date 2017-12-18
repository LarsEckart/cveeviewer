package ee.tublipoiss.cveeviewer.presentation.joblist;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.JobAdRepository;
import timber.log.Timber;

public class JobListPresenterImpl implements JobListPresenter {

    private final JobListView view;
    private JobAdRepository repository;

    @Inject
    public JobListPresenterImpl(JobListView view, JobAdRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {
        Timber.d("I'm alive");
        if (view != null) {
            Timber.d("i've a view!");
        }
        view.showLoading();
        repository.getJobAds();
    }
}
