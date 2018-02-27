package io.neverstoplearning.acviewmodel.networking;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static RepoService provideReposervice(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}
