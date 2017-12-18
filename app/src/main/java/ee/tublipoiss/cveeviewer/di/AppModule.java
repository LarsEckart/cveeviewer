package ee.tublipoiss.cveeviewer.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ee.tublipoiss.cveeviewer.data.JobAdRepository;
import ee.tublipoiss.cveeviewer.data.JobAdRepositoryImpl;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListActivityComponent;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragmentComponent;

@Module(subcomponents = {
        JobListActivityComponent.class,
        JobListFragmentComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    JobAdRepository provideRepository(JobAdRepositoryImpl repository) {
        return repository;
    }

    // provide okhttp and such from here
}
