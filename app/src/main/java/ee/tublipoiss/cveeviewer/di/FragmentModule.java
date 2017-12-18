package ee.tublipoiss.cveeviewer.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragment;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragmentModule;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector(modules = JobListFragmentModule.class)
    abstract JobListFragment bindJobListFragment();
}
