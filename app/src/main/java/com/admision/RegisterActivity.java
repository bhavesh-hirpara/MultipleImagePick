package com.admision;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.Toast;

import com.admision.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {


    Handler handler = new Handler();

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.editFirstName)
    AppCompatEditText editFirstName;

    @BindView(R.id.editLastName)
    AppCompatEditText editLastName;

    @BindView(R.id.editEmail)
    AppCompatEditText editEmail;

    @BindView(R.id.editPassword)
    AppCompatEditText editPassword;

    @BindView(R.id.editConfPassword)
    AppCompatEditText editConfPassword;

//    @BindView(R.id.tvCreateAccount)
//    TextView tvCreateAccount;

//    @BindView(R.id.tvForgotPass)
//    TextView tvForgotPass;

    boolean isRider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initDrawer(true);
        init();
    }

    private void init() {
//        initImageLoader();

    }

    ImageLoader imageLoader;

    private void initImageLoader() {
        try {
            imageLoader = Utils.initImageLoader(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validate() {
        if (editEmail.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_email), Toast.LENGTH_SHORT);
            return false;
        }

//        if (!Patterns.EMAIL_ADDRESS.matcher(editLoginEmail.getText()).matches()) {
//            showToast(getString(R.string.err_email_invalid), Toast.LENGTH_SHORT);
//            return false;
//        }

        if (editPassword.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_password), Toast.LENGTH_SHORT);
            return false;
        }

        return true;
    }


}
