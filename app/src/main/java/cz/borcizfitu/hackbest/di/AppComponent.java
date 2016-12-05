package cz.borcizfitu.hackbest.di;

import javax.inject.Singleton;

import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import dagger.Component;

/**
 * Main application component
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
@Singleton
@Component(modules = {AppModule.class, InteractorsModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
}
