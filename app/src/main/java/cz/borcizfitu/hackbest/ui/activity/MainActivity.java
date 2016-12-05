package cz.borcizfitu.hackbest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.ui.fragment.MainFragment;

/**
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.lay_main_container, new MainFragment(), MainFragment.TAG)
                    .commit();
        }
    }
}
