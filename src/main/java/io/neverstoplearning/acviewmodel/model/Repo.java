package io.neverstoplearning.acviewmodel.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */


@AutoValue
public abstract class Repo {

    public abstract long id();
    public abstract String name();
    @Nullable
    public abstract String description();
    public abstract User owner();
    @Json(name = "stargazers_count")
    public abstract long stars();
    @Json(name = "forks_count")
    public abstract long forks();

    public static JsonAdapter<Repo> moshiAdapter(Moshi moshi){
        return  new AutoValue_Repo.MoshiJsonAdapter(moshi);
    }

}
