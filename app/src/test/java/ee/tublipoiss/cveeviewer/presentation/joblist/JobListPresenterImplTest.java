package ee.tublipoiss.cveeviewer.presentation.joblist;

import org.junit.Before;
import org.junit.Test;

import ee.tublipoiss.cveeviewer.data.JobAdRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobListPresenterImplTest {

    private JobListView view;
    private JobAdRepository repository;
    private JobListPresenter presenter;

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
        verify(repository).getJobAds();
    }
}