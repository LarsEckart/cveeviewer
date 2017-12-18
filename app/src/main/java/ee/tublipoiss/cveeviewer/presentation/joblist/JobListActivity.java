package ee.tublipoiss.cveeviewer.presentation.joblist;

import android.app.Activity;
import android.os.Bundle;

import ee.tublipoiss.cveeviewer.R;
import timber.log.Timber;

public class JobListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_joblist);
    }
}

