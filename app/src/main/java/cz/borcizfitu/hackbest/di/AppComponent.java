package cz.borcizfitu.hackbest.di;

import javax.inject.Singleton;

import cz.borcizfitu.hackbest.mvp.presenter.LoginPresenter;
import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import cz.borcizfitu.hackbest.mvp.presenter.NewPackagePresenter;
import dagger.Component;

/**
 * Main application component
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
@Singleton
@Component(modules = {AppModule.class, InteractorsModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(NewPackagePresenter newPackagePresenter);
}
