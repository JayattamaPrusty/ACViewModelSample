package io.neverstoplearning.acviewmodel.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by mulasa.arunkumar on 27-02-2018.
 */

public class BaseApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationContext(Context context){
        return ((BaseApplication) context.getApplicationContext()).component;
    }
}
