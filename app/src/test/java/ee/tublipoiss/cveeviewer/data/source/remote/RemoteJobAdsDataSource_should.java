package ee.tublipoiss.cveeviewer.data.source.remote;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoteJobAdsDataSource_should {

    private RemoteJobAdsDataSource remoteJobAdsDataSource;
    private RemoteDataSourceResponseConverter converter;
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        String url = server.url("/job-ads/all").toString();

        converter = mock(RemoteDataSourceResponseConverter.class);

        remoteJobAdsDataSource = new RemoteJobAdsDataSource(url, converter);
    }

    @Test
    public void execute_http_request_to_endpoint() throws Exception {
        // given
        server.enqueue(new MockResponse().setResponseCode(200).setBody("hello, world!"));

        // when
        remoteJobAdsDataSource.getJobAds(new LoadJobAdsCallback() {
            @Override
            public void onJobAdsLoaded(List<JobAd> jobAds) {
                assertThat(true).isFalse();
            }
        });

        // then
        RecordedRequest request = server.takeRequest(1000, TimeUnit.SECONDS);
        assertThat(request.getPath()).isEqualTo("/job-ads/all");
    }

    @Test
    public void convert_response_to_our_model() throws Exception {
        // given
        //TODO: remove absolute path otherwise tests won't run in CI server
        File file = new File("/Users/larse/github/cveeviewer/app/src/test/resources/response.xml");
        InputStream targetStream = new FileInputStream(file);
        BufferedReader buf = new BufferedReader(new InputStreamReader(targetStream));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        String fileAsString = sb.toString();

        server.enqueue(new MockResponse().setBody(fileAsString).setResponseCode(200));

        // when
        remoteJobAdsDataSource.getJobAds(new LoadJobAdsCallback() {
            @Override
            public void onJobAdsLoaded(List<JobAd> jobAds) {
            }
        });


        // then
        verify(converter).convert(any(InputStream.class));
    }
}