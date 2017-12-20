package ee.tublipoiss.cveeviewer.presentation.joblist;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ee.tublipoiss.cveeviewer.data.JobAd;
import ee.tublipoiss.cveeviewer.data.source.JobAdRepository;
import ee.tublipoiss.cveeviewer.data.source.LoadJobAdsCallback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobListPresenterImpl_should {

    private JobListView view;
    private JobAdRepository repository;

    private JobListPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(JobListView.class);
        repository = mock(JobAdRepository.class);
        presenter = new JobListPresenterImpl(view, repository);
    }

    @Test
    public void display_loading_indicator_when_start() throws Exception {
        // given

        // when
        presenter.start();

        // then
        verify(view).showLoading();
    }

    @Test
    public void query_job_ads_from_repository() throws Exception {
        // given

        // when
        presenter.start();

        // then
        verify(repository).getJobAds(any(LoadJobAdsCallback.class));
    }

    @Test
    public void tell_view_on_job_ad_loaded_callback_to_display_job_ads() throws Exception {
        // given
        ArrayList<JobAd> jobAds = new ArrayList<>();

        // when
        presenter.onJobAdsLoaded(jobAds);

        // then
        verify(view).showJobAds(jobAds);
    }
}