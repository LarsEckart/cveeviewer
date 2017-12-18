package ee.tublipoiss.cveeviewer.presentation.joblist;

import android.os.Bundle;

import dagger.android.DaggerActivity;
import ee.tublipoiss.cveeviewer.R;
import timber.log.Timber;

public class JobListActivity extends DaggerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_joblist);
    }

}

