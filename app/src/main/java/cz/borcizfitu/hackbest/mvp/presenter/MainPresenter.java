package cz.borcizfitu.hackbest.mvp.presenter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.Constants;
import cz.borcizfitu.hackbest.RxBus;
import cz.borcizfitu.hackbest.client.ApiInterface;
import cz.borcizfitu.hackbest.client.DropboxClientFactory;
import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.interactor.IApiInteractor;
import cz.borcizfitu.hackbest.interactor.ISpInteractor;
import cz.borcizfitu.hackbest.mvp.presenter.base.BaseRxPresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter for {@link IMainView}.
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public class MainPresenter extends BaseRxPresenter<IMainView> {
    public static final String TAG = MainPresenter.class.getName();

    @Inject
    IApiInteractor apiInteractor;

    @Inject
    ISpInteractor spInteractor;

    @Inject
    RxBus rxBus;

    private List<Item> receivedPackages = new ArrayList<>();
    private List<Item> sentPackages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        App.getAppComponent().inject(this);

//        for (int i = 0; i < 10; i++){
//            Item item = new Item("Name" + i, "" + i, 1482192000, "Pantokrator", 0);
//            spInteractor.storePackage(item);
//        }
//
//        for (int i = 0; i < 10; i++){
//            Item item = new Item("Name" + i, "" + i, 1482192000, "Pantokrator", 1);
//            spInteractor.storePackage(item);
//        }
    }

    @Override
    protected void onTakeView(IMainView iMainView) {
        super.onTakeView(iMainView);
        loadPackages();
    }

    private void loadPackages() {
        List<Item> packages = spInteractor.getPackages();
        sentPackages.clear();
        receivedPackages.clear();
        for (Item pack: packages) {
            if(pack.getType() == Constants.TYPE_RECEIVED){
                receivedPackages.add(pack);
            } else {
                sentPackages.add(pack);
            }
        }

        getView().showPackages(receivedPackages, sentPackages);
    }

    public void removeReceivedPackage(String url) {
        for (Item item: receivedPackages) {
            if(TextUtils.equals(item.getUrl(), url)){
                receivedPackages.remove(item);
                List<Item> packages = new ArrayList<>();
                packages.addAll(receivedPackages);
                packages.addAll(sentPackages);
                spInteractor.storePackages(packages);
                getView().showPackages(receivedPackages, sentPackages);
                return;
            }
        }
    }

    public void removeSentPackage(String url) {
        for (Item item: sentPackages) {
            if(TextUtils.equals(item.getUrl(), url)){
                sentPackages.remove(item);
                List<Item> packages = new ArrayList<>();
                packages.addAll(receivedPackages);
                packages.addAll(sentPackages);
                spInteractor.storePackages(packages);
                getView().showPackages(receivedPackages, sentPackages);
                return;
            }
        }
    }
}
