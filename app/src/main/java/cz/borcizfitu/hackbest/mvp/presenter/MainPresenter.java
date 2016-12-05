package cz.borcizfitu.hackbest.mvp.presenter;

import android.os.Bundle;

import javax.inject.Inject;

import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.RxBus;
import cz.borcizfitu.hackbest.interactor.IApiInteractor;
import cz.borcizfitu.hackbest.mvp.presenter.base.BaseRxPresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;

/**
 * Presenter for {@link IMainView}.
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public class MainPresenter extends BaseRxPresenter<IMainView> {
    public static final String TAG = MainPresenter.class.getName();

    @Inject
    IApiInteractor apiInteractor;

    @Inject
    RxBus rxBus;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        App.getAppComponent().inject(this);
    }
}
