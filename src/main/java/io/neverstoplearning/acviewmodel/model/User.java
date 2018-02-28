package io.neverstoplearning.acviewmodel.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;


/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */


@AutoValue
public abstract class User {

    public abstract String login();

    public static JsonAdapter<User> moshiAdapter(Moshi moshi){
        return  new AutoValue_User.MoshiJsonAdapter(moshi);
    }


}
