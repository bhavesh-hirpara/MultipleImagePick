package com.admision;

import android.os.Bundle;
import android.widget.Toast;

import com.admision.utils.ExitStrategy;

import butterknife.ButterKnife;

public class MainActivity extends com.admision.BaseActivity {


//    @BindView(R.id.btnSendRequest)
//    Button btnSendRequest;
//
//    @BindView(R.id.editForgetEmail)
//    EditText editForgetEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initDrawer();
        init();
    }

    private void init() {
        setTitleText("Home");
    }


    @Override
    public void onBackPressed() {

        try {
            if (result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                if (ExitStrategy.canExit()) {
                    super.onBackPressed();
                } else {
                    ExitStrategy.startExitDelay(2000);
                    Toast.makeText(getActivity(), getString(R.string.exit_msg),
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {

        }

    }
}
