package cz.borcizfitu.hackbest.interactor;

import java.util.List;

import cz.borcizfitu.hackbest.domain.model.Item;
import rx.Observable;

/**
 * Interactor that communicates with api service
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public interface IApiInteractor {
    Observable<List<Integer>> obtainBestStoriesIdList();

    Observable<Item> obtainItem(int itemId);
}
