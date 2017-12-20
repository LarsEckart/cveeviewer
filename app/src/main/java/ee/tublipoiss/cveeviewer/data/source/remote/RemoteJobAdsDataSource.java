package ee.tublipoiss.cveeviewer.data.source.remote;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.JobAdsDataSource;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;

public class RemoteJobAdsDataSource implements JobAdsDataSource {

    private final String url;
    private final RemoteDataSourceResponseConverter converter;

    @Inject
    public RemoteJobAdsDataSource(String url, RemoteDataSourceResponseConverter converter) {
        this.url = url;
        this.converter = converter;
    }

    @Override
    public void getJobAds(LoadJobAdsCallback callback) {
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
            List<JobAd> jobAds = converter.convert(inputStream);

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
