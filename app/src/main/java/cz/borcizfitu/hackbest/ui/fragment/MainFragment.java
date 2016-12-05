package cz.borcizfitu.hackbest.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;
import cz.borcizfitu.hackbest.ui.fragment.base.BaseRetryNucleusFragment;
import cz.borcizfitu.hackbest.utils.UiUtils;
import nucleus.factory.RequiresPresenter;

/**
 * Fragment displaying feed of best stories.
 * Created by Jan Stanek[st.honza@gmail.com] on {13.11.16}
 **/
@RequiresPresenter(MainPresenter.class)
public class MainFragment extends BaseRetryNucleusFragment<MainPresenter> implements IMainView {
    public static final String TAG = MainFragment.class.getName();

    @BindView(R.id.recycler_feed)
    RecyclerView recycler;
    @BindView(R.id.text_feed_error)
    TextView textError;
    @BindView(R.id.swipe_feed)
    SwipeRefreshLayout swipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 0;
                outRect.top = UiUtils.dpToPx(getActivity(), 1);
                outRect.right = 0;
                outRect.left = 0;
            }
        });
    }
}
