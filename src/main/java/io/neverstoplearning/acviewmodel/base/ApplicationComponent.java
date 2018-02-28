package io.neverstoplearning.acviewmodel.base;

import javax.inject.Singleton;

import dagger.Component;
import io.neverstoplearning.acviewmodel.details.ListDetailsFragment;
import io.neverstoplearning.acviewmodel.home.ListFragment;
import io.neverstoplearning.acviewmodel.networking.NetworkModule;
import io.neverstoplearning.acviewmodel.viewmodels.ViewModelModule;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */

@Singleton
@Component(modules = {
        NetworkModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {

    void inject(ListFragment listFragment);

    void inject(ListDetailsFragment listDetailsFragment);
}
