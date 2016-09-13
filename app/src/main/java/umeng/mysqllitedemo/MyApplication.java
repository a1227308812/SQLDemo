package umeng.mysqllitedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by ZWP on 2016/8/25.
 * Explain:自定义的Application
 */
public class MyApplication extends Application{

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getMyAppContext() {
        return myApplication.getApplicationContext();
    }
}
