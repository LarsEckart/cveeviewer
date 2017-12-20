package ee.tublipoiss.cveeviewer.data.source.remote;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ee.tublipoiss.cveeviewer.data.JobAd;

public class RemoteDataSourceResponseConverter {

    @Inject
    public RemoteDataSourceResponseConverter() {
    }

    public List<JobAd> convert(InputStream inputStream) {
        return Collections.emptyList();
    }
}
