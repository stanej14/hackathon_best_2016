package cz.borcizfitu.hackbest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.gcm.GcmUtils;
import cz.borcizfitu.hackbest.ui.fragment.MainFragment;
import cz.borcizfitu.hackbest.ui.fragment.NewPackageFragment;


public class NewPackageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.lay_main_container, new NewPackageFragment(), NewPackageFragment.TAG)
                    .commit();
        }

        checkForClickingOnNotification();
    }

    public void checkForClickingOnNotification() {
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String content = bundle.getString(GcmUtils.GCM_CONTENT, "");
            Toast.makeText(NewPackageActivity.this, content, Toast.LENGTH_SHORT).show();
        }
    }
}
