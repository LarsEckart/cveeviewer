package ee.tublipoiss.cveeviewer.presentation.fragments;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import timber.log.Timber;

/**
 * Created by lars on 02/05/14.
 */
public class ContentFetcher {
    private String contentUrl;

    public void setUrl(String url) {
        this.contentUrl = url;
    }

    public InputStream fetch() {
        try {
            Timber.d("requesting data from %s", contentUrl);
            URL oracle = new URL(contentUrl);
            URLConnection yc = oracle.openConnection();
            InputStream inputStream = yc.getInputStream();
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }
}
