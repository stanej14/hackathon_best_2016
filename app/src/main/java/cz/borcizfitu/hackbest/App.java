package cz.borcizfitu.hackbest;

import android.app.Application;

import cz.borcizfitu.hackbest.di.AppComponent;
import cz.borcizfitu.hackbest.di.AppModule;
import cz.borcizfitu.hackbest.di.DaggerAppComponent;
import cz.borcizfitu.hackbest.di.InteractorsModule;
import timber.log.Timber;


/**
 * Application class for this application
 * <p/>
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 */
public class App extends Application {

    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .interactorsModule(new InteractorsModule())
                .build();

        // Initialize Timber
        Timber.plant(new Timber.DebugTree());
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
