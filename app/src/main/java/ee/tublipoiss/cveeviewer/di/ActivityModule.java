package ee.tublipoiss.cveeviewer.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListActivity;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListActivityModule;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = JobListActivityModule.class)
    abstract JobListActivity bindJobListActivity();

}
