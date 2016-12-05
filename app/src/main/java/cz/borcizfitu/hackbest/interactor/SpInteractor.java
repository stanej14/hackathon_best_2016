package cz.borcizfitu.hackbest.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Implementation of {@link ISpInteractor}
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public class SpInteractor implements ISpInteractor {
    public static final String TAG = SpInteractor.class.getName();
    private static final String SP_NAME = "hack_best_preferences";

    private final SharedPreferences sharedPreferences;

    public SpInteractor(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
}
