package cz.borcizfitu.hackbest.interactor;

import java.util.List;

import cz.borcizfitu.hackbest.domain.model.Item;

/**
 * Interactor that communicates with {@link android.content.SharedPreferences}
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public interface ISpInteractor {
    List<Item> getPackages();

    void storePackage(Item item);

    void storePackages(List<Item> packages);
}
