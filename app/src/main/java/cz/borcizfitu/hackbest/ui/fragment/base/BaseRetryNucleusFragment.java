package cz.borcizfitu.hackbest.ui.fragment.base;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;

import java.util.UUID;

import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.mvp.presenter.base.BaseRxPresenter;
import cz.borcizfitu.hackbest.mvp.view.base.IShowRetryView;
import cz.borcizfitu.hackbest.utils.NetworkUtils;
import nucleus.view.NucleusFragment;

/**
 * Base fragment that implements {@link IShowRetryView} so that it's able to handle retrying of observables.
 * <p>
 * Created by Jan Stanek[st.honza@gmail.com] on {28.6.16}
 **/
public abstract class BaseRetryNucleusFragment<P extends BaseRxPresenter> extends NucleusFragment<P> implements IShowRetryView {

    /**
     * Show SnackBar with error message and retry button.
     *
     * @param throwable Throwable used to create an error message.
     */
    @Override
    public void showRetry(@NonNull Throwable throwable, @NonNull final UUID uuid) {
        if (getView() != null) {
            String message = NetworkUtils.createErrorMessage(getActivity(), throwable);
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry, v -> {
                        getPresenter().onRetryAccepted(uuid);
                    })
                    .setActionTextColor(ContextCompat.getColor(getActivity(), R.color.green))
                    .setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            if (event != DISMISS_EVENT_ACTION) {
                                getPresenter().onRetryDenied(uuid);
                            }
                        }
                    })
                    .show();
        } else {
            getPresenter().onRetryDenied(uuid);
        }
    }
}
