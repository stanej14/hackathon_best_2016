package cz.borcizfitu.hackbest.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.borcizfitu.hackbest.domain.model.Item;
import rx.Observable;

/**
 * Implementation of {@link ISpInteractor}
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public class SpInteractor implements ISpInteractor {
    public static final String TAG = SpInteractor.class.getName();
    private static final String SP_NAME = "hack_best_preferences";
    private static final String PACKAGES = "packages";
    private static final String SP_ACCESS_TOKEN = "access_token";

    private final SharedPreferences sharedPreferences;

    public SpInteractor(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void storePackage(Item pack) {
        List<Item> packs = getPackages();
        if(packs == null){
            packs = new ArrayList<>();
        }
        packs.add(pack);

        Set<String> stringSet = new HashSet<>();
        for (Item item: packs) {
            try {
                stringSet.add(toString(item));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sharedPreferences.edit().putStringSet(PACKAGES, stringSet).apply();
    }

    @Override
    public void storePackages(List<Item> packages) {
        Set<String> stringSet = new HashSet<>();
        for (Item item: packages) {
            try {
                stringSet.add(toString(item));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sharedPreferences.edit().putStringSet(PACKAGES, stringSet).apply();
    }

    @Override
    public List<Item> getPackages() {
        Set<String> stringSet = sharedPreferences.getStringSet(PACKAGES, null);
        if(stringSet == null){
            return null;
        }
        List<Item> items = new ArrayList<>();
        for (String string: stringSet) {
            items.add(toItem(string));
        }
        return items;
    }

    private static String toString( Item item) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(
                    new Base64OutputStream(baos, Base64.NO_PADDING
                            | Base64.NO_WRAP));
            oos.writeObject(item);
            oos.close();
            return baos.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static Item toItem(String string) {
        try {
            return (Item) new ObjectInputStream(new Base64InputStream(
                    new ByteArrayInputStream(string.getBytes()), Base64.NO_PADDING
                    | Base64.NO_WRAP)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAccessToken() {
        return sharedPreferences.getString(SP_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        sharedPreferences.edit().putString(SP_ACCESS_TOKEN, accessToken).apply();
    }
}
