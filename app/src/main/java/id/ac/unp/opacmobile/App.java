package id.ac.unp.opacmobile;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

