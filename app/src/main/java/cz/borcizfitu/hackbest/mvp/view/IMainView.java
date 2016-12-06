package cz.borcizfitu.hackbest.mvp.view;

import java.util.List;

import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import cz.borcizfitu.hackbest.mvp.view.base.IShowRetryView;

/**
 * View for {@link MainPresenter}.
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public interface IMainView extends IShowRetryView {
    void showPackages(List<Item> receivedPackages, List<Item> sentPackages);
}
