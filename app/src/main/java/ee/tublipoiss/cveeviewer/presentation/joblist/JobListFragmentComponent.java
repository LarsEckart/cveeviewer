package ee.tublipoiss.cveeviewer.presentation.joblist;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragmentModule;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragment;

@Subcomponent(modules = JobListFragmentModule.class)
public interface JobListFragmentComponent extends AndroidInjector<JobListFragment> {


    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<JobListFragment> {
    }
}
