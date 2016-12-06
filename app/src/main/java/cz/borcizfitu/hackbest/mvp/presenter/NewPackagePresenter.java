package cz.borcizfitu.hackbest.mvp.presenter;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.Constants;
import cz.borcizfitu.hackbest.RxBus;
import cz.borcizfitu.hackbest.client.ApiInterface;
import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.gcm.GcmUtils;
import cz.borcizfitu.hackbest.interactor.IApiInteractor;
import cz.borcizfitu.hackbest.interactor.ISpInteractor;
import cz.borcizfitu.hackbest.mvp.presenter.base.BaseRxPresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;
import cz.borcizfitu.hackbest.mvp.view.INewPackageView;
import rx.Observable;
import rx.schedulers.Schedulers;
import timber.log.Timber;


public class NewPackagePresenter extends BaseRxPresenter<INewPackageView> {
    public static final String TAG = NewPackagePresenter.class.getName();

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    @Inject
    ISpInteractor spInteractor;

    List<String> names = new ArrayList<>();
    List<String> paths = new ArrayList<>();
    List<File> files = new ArrayList<>();
    List<Long> sizes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        App.getAppComponent().inject(this);
    }

    public void addFile(String fileName, String path, File myFile, long size) {
        names.add(fileName);
        paths.add(path);
        files.add(myFile);
        sizes.add(size);
    }

    public void removeFile(String name) {
        for(int i = 0; i < names.size(); i++){
            if(TextUtils.equals(names.get(i), name)){
                names.remove(i);
                paths.remove(i);
                files.remove(i);
                sizes.remove(i);
                getView().updateFiles(names, sizes);
                return;
            }
        }
    }

    public void uploadFles() {
        names.clear();
        paths.clear();
        files.clear();
        sizes.clear();
        getView().showProgress();
        String receiver = "dpSjOpfSSTE:APA91bErN95xqn6gXidMPoi99erVrEGC2S-VBPw5oIlHf6gYAtzfT0HlGsXK2cggToHdioD7oL2lYvnLHblIPwIyYt3osBx1quvLT_2mgF-xE_B6BJcXlqmly3M-UM62zvhBVR5iO6Sn";
        Observable.just(receiver)
                .subscribeOn(Schedulers.io())
                .doOnNext(r -> {
                    try {
                        JSONObject notification = GcmUtils.createNotification("Nová zásilka");
                        GcmUtils.sendMessage(notification, r);
                    } catch (Exception e) {
                        Timber.e("", e);
                    }
                })
                .subscribe(s -> { succ();
                }, Throwable::printStackTrace);
    }

    private void succ() {
        Runnable task = new Runnable() {
            public void run() {
                getView().dismissProgressDialog();
                getView().updateFiles(names,sizes);
                getView().clearName();
            }
        };
        worker.schedule(task, 3, TimeUnit.SECONDS);
    }
}
