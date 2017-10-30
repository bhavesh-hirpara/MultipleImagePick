package com.admision;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity {


    @BindView(R.id.btnSendRequest)
    Button btnSendRequest;

    @BindView(R.id.editForgetEmail)
    EditText editForgetEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

    }

    @OnClick(R.id.btnSendRequest)
    public void sendRequest(View v) {

        if (validate()) {

        }
    }

    private boolean validate() {

        if (editForgetEmail.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_email), Toast.LENGTH_SHORT);
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(editForgetEmail.getText()).matches()) {
            showToast(getString(R.string.err_email_invalid), Toast.LENGTH_SHORT);
            return false;
        }

        return true;
    }


}
