package ee.tublipoiss.cveeviewer.data.source.remote;

import java.io.InputStream;
import java.util.List;

import ee.tublipoiss.cveeviewer.data.JobAd;

public interface RemoteDataSourceResponseConverter {

    public List<JobAd> convert(InputStream inputStream);
}
