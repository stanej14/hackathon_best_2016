package cz.borcizfitu.hackbest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dropbox.core.android.Auth;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.domain.rest.ApiConfig;
import cz.borcizfitu.hackbest.mvp.presenter.LoginPresenter;
import cz.borcizfitu.hackbest.mvp.view.ILoginView;
import cz.borcizfitu.hackbest.ui.fragment.base.BaseRetryNucleusFragment;
import nucleus.factory.RequiresPresenter;

/**
 * Created by krata on 6.12.16.
 */

@RequiresPresenter(LoginPresenter.class)
public class LoginFragment extends BaseRetryNucleusFragment<LoginPresenter> implements ILoginView {
    public static final String TAG = LoginFragment.class.getName();

    @BindView(R.id.webview_login)
    WebView webViewLogin;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Auth.startOAuth2Authentication(getActivity(), ApiConfig.APP_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().auth(getActivity());
    }
}
