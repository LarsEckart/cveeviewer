package ee.tublipoiss.cveeviewer.data.source.remote;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.source.JobAdsDataSource;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;

public class RemoteJobAdsDataSource implements JobAdsDataSource {

    private final String url;

    @Inject
    public RemoteJobAdsDataSource(String url) {
        this.url = url;
    }

    @Override
    public void getJobAds(LoadJobAdsCallback callback) {
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
