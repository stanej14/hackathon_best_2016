package cz.borcizfitu.hackbest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.ui.fragment.LoginFragment;

/**
 * Created by krata on 6.12.16.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new LoginFragment(), LoginFragment.TAG)
                    .commit();
        }
    }
}
