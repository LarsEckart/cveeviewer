package ee.tublipoiss.cveeviewer.data;

import java.util.List;

public interface LoadJobAdsCallback {

    void onJobAdsLoaded(List<JobAd> jobAds);
}
