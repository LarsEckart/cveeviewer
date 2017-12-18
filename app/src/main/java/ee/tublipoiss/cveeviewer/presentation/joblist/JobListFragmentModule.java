package ee.tublipoiss.cveeviewer.presentation.joblist;

import dagger.Module;
import dagger.Provides;

@Module
public class JobListFragmentModule {

    @Provides
    JobListView provideJobListView(JobListFragment fragment) {
        return fragment;
    }

    @Provides
    JobListPresenter provideJobListPresenter(final JobListPresenterImpl presenter) {
        return presenter;
    }
}
