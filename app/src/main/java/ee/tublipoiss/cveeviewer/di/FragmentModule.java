package ee.tublipoiss.cveeviewer.di;

import android.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragmentComponent;
import ee.tublipoiss.cveeviewer.presentation.joblist.JobListFragment;

@Module
public abstract class FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(JobListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindJobListFragment(JobListFragmentComponent.Builder builder);
}
