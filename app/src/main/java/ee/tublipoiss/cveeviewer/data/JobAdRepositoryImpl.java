package ee.tublipoiss.cveeviewer.data;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class JobAdRepositoryImpl implements JobAdRepository {

    @Inject
    public JobAdRepositoryImpl() {
    }

    @Override
    public List<JobAd> getJobAds() {
        return Collections.emptyList();
    }
}
