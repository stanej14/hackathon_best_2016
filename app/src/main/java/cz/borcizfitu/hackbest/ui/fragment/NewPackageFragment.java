package cz.borcizfitu.hackbest.ui.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.domain.model.Item;
import cz.borcizfitu.hackbest.mvp.presenter.MainPresenter;
import cz.borcizfitu.hackbest.mvp.presenter.NewPackagePresenter;
import cz.borcizfitu.hackbest.mvp.view.IMainView;
import cz.borcizfitu.hackbest.mvp.view.INewPackageView;
import cz.borcizfitu.hackbest.ui.adapter.MergeRecyclerAdapter;
import cz.borcizfitu.hackbest.ui.adapter.ReceivedAdapter;
import cz.borcizfitu.hackbest.ui.adapter.SentAdapter;
import cz.borcizfitu.hackbest.ui.fragment.base.BaseRetryNucleusFragment;
import nucleus.factory.RequiresPresenter;


@RequiresPresenter(NewPackagePresenter.class)
public class NewPackageFragment extends BaseRetryNucleusFragment<NewPackagePresenter> implements INewPackageView {
    public static final String TAG = NewPackageFragment.class.getName();
    private static final int YOUR_RESULT_CODE = 1;


    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.added_items)
    LinearLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_package, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.add)
    public void onAddClicked() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, YOUR_RESULT_CODE);
    }

    @OnClick(R.id.send)
    public void onSendClicked() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YOUR_RESULT_CODE) {
            if (resultCode == -1) {
                String fileName;
                Uri uri = data.getData();
                fileName = uri.getLastPathSegment();
                File myFile = new File(uri.toString());
                String path = myFile.getAbsolutePath();
                getPresenter().addFile(fileName, path, myFile);

                View someLayoutView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.sent_item, null);

                TextView name = (TextView) someLayoutView.findViewById(R.id.text_item_title);
                Button btn = (Button) someLayoutView.findViewById(R.id.img_btn_remove);

                name.setText(fileName);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().removeFile(fileName);
                    }
                });

                layout.addView(someLayoutView);
            }
        }
    }

    @Override
    public void updateFiles(List<String> names) {
        if (null != layout && layout.getChildCount() > 0) {
            try {
                layout.removeViews (0, layout.getChildCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (String name: names) {
            View someLayoutView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.sent_item, null);

            TextView name2 = (TextView) someLayoutView.findViewById(R.id.text_item_title);
            Button btn = (Button) someLayoutView.findViewById(R.id.img_btn_remove);

            name2.setText(name);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().removeFile(name);
                }
            });

            layout.addView(someLayoutView);
        }
    }
}
