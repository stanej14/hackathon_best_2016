package cz.borcizfitu.hackbest.mvp.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.Constants;
import cz.borcizfitu.hackbest.RxBus;
import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.interactor.IApiInteractor;
import cz.borcizfitu.hackbest.interactor.ISpInteractor;
import cz.borcizfitu.hackbest.mvp.presenter.base.BaseRxPresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;
import cz.borcizfitu.hackbest.mvp.view.INewPackageView;


public class NewPackagePresenter extends BaseRxPresenter<INewPackageView> {
    public static final String TAG = NewPackagePresenter.class.getName();

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
}
