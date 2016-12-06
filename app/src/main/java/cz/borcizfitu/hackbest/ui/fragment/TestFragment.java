package cz.borcizfitu.hackbest.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.gcm.GcmUtils;
import rx.Observable;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * TODO add class description
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {6.12.16}
 **/
public class TestFragment extends Fragment {
    public static final String TAG = TestFragment.class.getName();
    @BindView(R.id.btn_test_register)
    Button btnTestRegister;
    @BindView(R.id.btn_test_send)
    Button btnTestSend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnTestRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get token
                String token = FirebaseInstanceId.getInstance().getToken();

                // Log and toast
                String msg = "Token = " + token;
                Log.d(TAG, msg);
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnTestSend.setOnClickListener(v -> {
            String receiver = "dpSjOpfSSTE:APA91bErN95xqn6gXidMPoi99erVrEGC2S-VBPw5oIlHf6gYAtzfT0HlGsXK2cggToHdioD7oL2lYvnLHblIPwIyYt3osBx1quvLT_2mgF-xE_B6BJcXlqmly3M-UM62zvhBVR5iO6Sn";
            Observable.just(receiver)
                    .subscribeOn(Schedulers.io())
                    .doOnNext(r -> {
                        try {
                            JSONObject notification = GcmUtils.createNotification("Nová zásilka");
                            GcmUtils.sendMessage(notification, r);
                        } catch (Exception e) {
                            Timber.e("", e);
                        }
                    })
                    .subscribe(s -> {}, Throwable::printStackTrace);
        });
    }
}
