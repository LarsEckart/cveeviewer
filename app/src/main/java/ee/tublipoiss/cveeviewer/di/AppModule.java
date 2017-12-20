package ee.tublipoiss.cveeviewer.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ee.tublipoiss.cveeviewer.data.JobAdParser;
import ee.tublipoiss.cveeviewer.data.source.JobAdRepository;
import ee.tublipoiss.cveeviewer.data.source.remote.RemoteDataSourceResponseConverter;
import ee.tublipoiss.cveeviewer.data.source.remote.RemoteJobAdsDataSource;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    JobAdRepository provideRepository(RemoteJobAdsDataSource ds) {
        return new JobAdRepository(ds);
    }

    @Provides
    @Singleton
    RemoteJobAdsDataSource provideRemoteDataSource(
            @Named("baseUrl") String baseUrl, RemoteDataSourceResponseConverter converter) {
        return new RemoteJobAdsDataSource(baseUrl, converter);
    }

    @Provides
    @Singleton
    RemoteDataSourceResponseConverter provideConverter() {
        return new JobAdParser();
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return "https://www.cv.ee/";
    }

    // provide okhttp and such from here
}
