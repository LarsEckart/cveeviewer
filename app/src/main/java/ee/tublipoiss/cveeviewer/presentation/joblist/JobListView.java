package ee.tublipoiss.cveeviewer.presentation.joblist;


import java.util.List;

import ee.tublipoiss.cveeviewer.data.JobAd;

public interface JobListView {

    void showLoading();

    void showJobAds(List<JobAd> jobAds);
}
