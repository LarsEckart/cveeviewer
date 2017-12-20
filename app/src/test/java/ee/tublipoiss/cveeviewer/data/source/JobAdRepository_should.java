package ee.tublipoiss.cveeviewer.data.source;

import org.junit.Test;

import java.util.List;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.remote.RemoteJobAdsDataSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobAdRepository_should {

    @Test
    public void load_job_ads_from_remote_data_source() throws Exception {
        // given
        RemoteJobAdsDataSource remoteDataSource = mock(RemoteJobAdsDataSource.class);
        JobAdRepository jobAdRepository = new JobAdRepository(remoteDataSource);

        // when
        jobAdRepository.getJobAds(new LoadJobAdsCallback() {
            @Override
            public void onJobAdsLoaded(List<JobAd> jobAds) {

            }
        });

        // then
        verify(remoteDataSource).getJobAds(any(LoadJobAdsCallback.class));
    }
}