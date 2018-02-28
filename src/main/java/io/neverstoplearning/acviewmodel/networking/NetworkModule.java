package io.neverstoplearning.acviewmodel.networking;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.neverstoplearning.acviewmodel.model.ModelAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */
@Module
public abstract class NetworkModule {
    private static final String BASE_URL = "https://api.github.com/";

    @Provides
    @Singleton
    static Moshi providesMoshi(){
        return new Moshi.Builder()
                .add(ModelAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    @Provides
    @Singleton
    static RepoService provideReposervice(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}
