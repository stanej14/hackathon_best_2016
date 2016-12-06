package cz.borcizfitu.hackbest.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.mvp.presenter.NewPackagePresenter;
import cz.borcizfitu.hackbest.mvp.view.INewPackageView;
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
                File myFile = new File(uri.getPath());
                long size = getFolderSize(myFile);
                fileName = uri.getLastPathSegment();
                String path = myFile.getAbsolutePath();
                getPresenter().addFile(fileName, path, myFile, size);


                View someLayoutView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.upload_item, null);

                TextView name = (TextView) someLayoutView.findViewById(R.id.text_item_title);
                Button btn = (Button) someLayoutView.findViewById(R.id.img_btn_remove);
                TextView sizeTV = (TextView) someLayoutView.findViewById(R.id.size);
                sizeTV.setText(getMB(size));

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

    public static long getFolderSize(File f) {
        long size = 0;
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                size += getFolderSize(file);
            }
        } else {
            size = f.length();
        }
        return size;
    }

    private String getMB(long size) {
        long Kb = 1 * 1024;
        long Mb = Kb * 1024;
        return size / Mb + " MB";
    }

    @Override
    public void updateFiles(List<String> names, List<Long> sizes) {
        if (null != layout && layout.getChildCount() > 0) {
            try {
                layout.removeViews(0, layout.getChildCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < names.size(); i++) {
            View someLayoutView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.upload_item, null);

            TextView name2 = (TextView) someLayoutView.findViewById(R.id.text_item_title);
            Button btn = (Button) someLayoutView.findViewById(R.id.img_btn_remove);
            TextView sizeTV = (TextView) someLayoutView.findViewById(R.id.size);
            sizeTV.setText(getMB(sizes.get(i)));

            name2.setText(names.get(i));
            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().removeFile(names.get(finalI));
                }
            });

            layout.addView(someLayoutView);
        }
    }
}
