package ee.tublipoiss.cveeviewer.data.source.remote;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.JobAdsDataSource;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;

public class RemoteJobAdsDataSource implements JobAdsDataSource {

    private final String url;
    private final RemoteDataSourceResponseConverter converter;
    private final OkHttpClient client;

    @Inject
    public RemoteJobAdsDataSource(String url, RemoteDataSourceResponseConverter converter, OkHttpClient client) {
        this.url = url;
        this.converter = converter;
        this.client = client;
    }

    @Override
    public void getJobAds(final LoadJobAdsCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/xml")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // TODO:
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    InputStream inputStream = responseBody.byteStream();
                    List<JobAd> jobAds = converter.convert(inputStream);
                    Timber.d("found " + jobAds.size() + " jobs");

                    callback.onJobAdsLoaded(jobAds);

                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Timber.d(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                }
            }
        });
    }

}
