package cz.borcizfitu.hackbest.di;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import cz.borcizfitu.hackbest.domain.rest.ApiDescription;
import cz.borcizfitu.hackbest.interactor.ApiInteractor;
import cz.borcizfitu.hackbest.interactor.IApiInteractor;
import cz.borcizfitu.hackbest.interactor.ISpInteractor;
import cz.borcizfitu.hackbest.interactor.SpInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Module that handles injecting Interactors
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 */
@Module(
        includes = {ServiceModule.class}
)
public class InteractorsModule {

    @Provides
    @Singleton
    public IApiInteractor provideApiInteractor(@NonNull ApiDescription apiDescription) {
        return new ApiInteractor(apiDescription);
    }

    @Provides
    @Singleton
    public ISpInteractor provideSPInteractor(@NonNull Application app) {
        return new SpInteractor(app.getApplicationContext());
    }
}
