package cv.tublipoiss.ee.cveeviewer.ui;

import android.app.Activity;
import android.os.Bundle;

import cv.tublipoiss.ee.cveeviewer.R;
import timber.log.Timber;

public class JobListActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_joblist);
    }

}

