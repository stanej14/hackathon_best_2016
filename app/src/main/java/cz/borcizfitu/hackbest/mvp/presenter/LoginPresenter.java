package cz.borcizfitu.hackbest.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dropbox.core.android.Auth;

import javax.inject.Inject;

import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.client.DropboxClientFactory;
import cz.borcizfitu.hackbest.domain.rest.ApiConfig;
import cz.borcizfitu.hackbest.interactor.ISpInteractor;
import cz.borcizfitu.hackbest.mvp.presenter.base.BaseRxPresenter;
import cz.borcizfitu.hackbest.mvp.view.ILoginView;
import cz.borcizfitu.hackbest.ui.activity.LoginActivity;
import cz.borcizfitu.hackbest.ui.activity.MainActivity;
import rx.Observable;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by krata on 6.12.16.
 */

public class LoginPresenter extends BaseRxPresenter<ILoginView> {

    @Inject
    ISpInteractor spInteractor;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        App.getAppComponent().inject(this);
    }

    public void auth(Context context) {
        Observable.just(context).subscribeOn(Schedulers.io()).subscribe(c -> {
            Timber.i("Getting access token");
            String accessToken = spInteractor.getAccessToken();
            if (accessToken == null) {
                accessToken = Auth.getOAuth2Token();
                spInteractor.setAccessToken(accessToken);
            }
            DropboxClientFactory.init(accessToken);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        }, Throwable::printStackTrace);
    }

    public boolean hasAccessToken() {
        return spInteractor.getAccessToken() != null;
    }
}
