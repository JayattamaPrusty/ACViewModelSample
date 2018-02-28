package io.neverstoplearning.acviewmodel.model;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Created by mulasa.arunkumar on 28-02-2018.
 */
@MoshiAdapterFactory
public abstract  class ModelAdapterFactory implements JsonAdapter.Factory{

    public static JsonAdapter.Factory create(){
        return new AutoValueMoshi_ModelAdapterFactory();
    }

}
