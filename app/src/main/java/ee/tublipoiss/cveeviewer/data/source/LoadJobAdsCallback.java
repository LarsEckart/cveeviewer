package ee.tublipoiss.cveeviewer.data.source;

import java.util.List;

import ee.tublipoiss.cveeviewer.data.JobAd;

public interface LoadJobAdsCallback {

    void onJobAdsLoaded(List<JobAd> jobAds);
}
