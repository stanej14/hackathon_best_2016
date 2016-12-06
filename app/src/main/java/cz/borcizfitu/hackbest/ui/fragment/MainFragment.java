package cz.borcizfitu.hackbest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;
import cz.borcizfitu.hackbest.ui.adapter.MergeRecyclerAdapter;
import cz.borcizfitu.hackbest.ui.adapter.ReceivedAdapter;
import cz.borcizfitu.hackbest.ui.adapter.SentAdapter;
import cz.borcizfitu.hackbest.ui.fragment.base.BaseRetryNucleusFragment;
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter;
import nucleus.factory.RequiresPresenter;

/**
 * Fragment displaying feed of best stories.
 * Created by Jan Stanek[st.honza@gmail.com] on {13.11.16}
 **/
@RequiresPresenter(MainPresenter.class)
public class MainFragment extends BaseRetryNucleusFragment<MainPresenter> implements IMainView,
        ReceivedAdapter.ReceivePackageListener, SentAdapter.SentPackageListener {
    public static final String TAG = MainFragment.class.getName();

    @BindView(R.id.recycler_packages)
    RecyclerView mMergeView;
    // Create a new merge adapter.

    private MergeRecyclerAdapter mergeAdapter;
    private ReceivedAdapter mReceivedAdapter;
    private SentAdapter mSentAdapter;
    private TextView receiveHeader;
    private TextView sentHeader;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mergeAdapter = new MergeRecyclerAdapter();

        mReceivedAdapter = new ReceivedAdapter(this);

        mSentAdapter = new SentAdapter(this);

        receiveHeader = (TextView) inflater.inflate(R.layout.receive_header, null,
                false);
        sentHeader = (TextView) inflater.inflate(R.layout.sent_header, null, false);

        mergeAdapter.addView(receiveHeader);
        mergeAdapter.addAdapter(mReceivedAdapter);
        mergeAdapter.addView(sentHeader);
        mergeAdapter.addAdapter(mSentAdapter);
        mMergeView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mMergeView.setAdapter(mergeAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showPackages(List<Item> receivedPackages, List<Item> sentPackages) {
        mReceivedAdapter.setItems(receivedPackages);
        mSentAdapter.setItems(sentPackages);

        mergeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onAddClicked(@NonNull String url) {
        // TODO download prom url
    }

    @Override
    public void onDeleteClicked(@NonNull String url) {
        getPresenter().removeReceivedPackage(url);
    }

    @Override
    public void onDeleteSentPackageClicked(@NonNull String url) {
        getPresenter().removeSentPackage(url);
    }
}
