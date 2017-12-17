package cv.tublipoiss.ee.cveeviewer.ui.fragments;

import android.os.Bundle;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lars on 06/12/2017.
 */
public class JobListFragmentTest {

  @Test public void exploration() throws Exception {
    JobListFragment jobListFragment = new JobListFragment();

    jobListFragment.onActivityCreated(new Bundle(1));
  }
}