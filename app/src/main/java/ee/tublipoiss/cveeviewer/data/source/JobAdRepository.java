package ee.tublipoiss.cveeviewer.data.source;

import java.util.List;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.remote.RemoteJobAdsDataSource;

public class JobAdRepository implements JobAdsDataSource {

    private RemoteJobAdsDataSource remoteDataSource;

    @Inject
    public JobAdRepository(RemoteJobAdsDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getJobAds(final LoadJobAdsCallback callback) {
        remoteDataSource.getJobAds(new LoadJobAdsCallback() {
            @Override
            public void onJobAdsLoaded(List<JobAd> jobAds) {
                callback.onJobAdsLoaded(jobAds);
            }
        });
    }
}
