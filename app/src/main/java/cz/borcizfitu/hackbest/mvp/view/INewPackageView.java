package cz.borcizfitu.hackbest.mvp.view;

import java.util.List;

import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import cz.borcizfitu.hackbest.mvp.view.base.IShowRetryView;


public interface INewPackageView  {
    void updateFiles(List<String> names, List<Long> sizes);
}
