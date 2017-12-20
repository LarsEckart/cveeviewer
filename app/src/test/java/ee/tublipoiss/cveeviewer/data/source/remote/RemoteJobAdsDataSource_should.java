package ee.tublipoiss.cveeviewer.data.source.remote;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoteJobAdsDataSource_should {
    
    @Test
    public void execute_http_request_to_endpoint() throws Exception {
        // given
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody("hello, world!"));
        server.start();
        HttpUrl baseUrl = server.url("/job-ads/all");
        RemoteJobAdsDataSource remoteJobAdsDataSource = new RemoteJobAdsDataSource(baseUrl.toString());

        // when
        remoteJobAdsDataSource.getJobAds(new LoadJobAdsCallback() {
            @Override
            public void onJobAdsLoaded(List<JobAd> jobAds) {

            }
        });

        // then
        RecordedRequest request = server.takeRequest(1000, TimeUnit.SECONDS);
        assertThat(request.getPath()).isEqualTo("/job-ads/all");
    }
}