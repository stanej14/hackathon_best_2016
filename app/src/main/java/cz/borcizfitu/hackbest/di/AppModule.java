package cz.borcizfitu.hackbest.di;

import android.app.Application;

import javax.inject.Singleton;

import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.RxBus;
import dagger.Module;
import dagger.Provides;

/**
 * Module that handle injecting application class (ie Context)
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 */
@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    RxBus provideBus() {
        return new RxBus();
    }
}
