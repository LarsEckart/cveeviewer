package ee.tublipoiss.cveeviewer.presentation.joblist;

import javax.inject.Inject;

import timber.log.Timber;

public class JobListPresenterImpl implements JobListPresenter {

    private final JobListView view;

    @Inject
    public JobListPresenterImpl(JobListView view) {
        this.view = view;
    }

    @Override
    public void start() {
        Timber.d("I'm alive");
        if (view != null) {
            Timber.d("i've a view!");
        }
    }
}
