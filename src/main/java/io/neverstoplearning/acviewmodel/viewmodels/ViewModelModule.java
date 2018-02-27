package io.neverstoplearning.acviewmodel.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.neverstoplearning.acviewmodel.details.RepoViewModel;
import io.neverstoplearning.acviewmodel.home.ListViewModel;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel.class)
    abstract ViewModel bindSelectedViewModel(RepoViewModel repoViewModel);
}
