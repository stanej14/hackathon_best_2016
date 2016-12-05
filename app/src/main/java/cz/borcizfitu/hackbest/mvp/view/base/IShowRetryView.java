package cz.borcizfitu.hackbest.mvp.view.base;

import java.util.UUID;

/**
 * Interface for views able to retry request.
 * Created by Jan Stanek[st.honza@gmail.com] on {13.11.16}
 **/
public interface IShowRetryView {
    void showRetry(Throwable throwable, UUID uuid);
}
