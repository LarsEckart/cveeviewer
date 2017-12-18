package ee.tublipoiss.cveeviewer.presentation.joblist;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = JobListActivityModule.class)
public interface JobListActivityComponent extends AndroidInjector<JobListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<JobListActivity> {
    }
}
