package ee.tublipoiss.cveeviewer.di;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ee.tublipoiss.cveeviewer.BuildConfig;
import ee.tublipoiss.cveeviewer.data.JobAdParser;
import ee.tublipoiss.cveeviewer.data.source.JobAdRepository;
import ee.tublipoiss.cveeviewer.data.source.remote.RemoteDataSourceResponseConverter;
import ee.tublipoiss.cveeviewer.data.source.remote.RemoteJobAdsDataSource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
            @Named("baseUrl") String baseUrl,
            RemoteDataSourceResponseConverter converter,
            OkHttpClient okHttpClient) {
        return new RemoteJobAdsDataSource(baseUrl, converter, okHttpClient);
    }

    @Provides
    @Singleton
    RemoteDataSourceResponseConverter provideConverter() {
        return new JobAdParser();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);

            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return "https://www.cv.ee/job-ads/all?type=rss&Tegvk=information-technology&Location=tartu-county&Town=tartu-county-tartu&Language=inglise";
    }

}
