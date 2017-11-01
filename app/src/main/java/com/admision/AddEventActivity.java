package com.admision;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.admision.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEventActivity extends BaseActivity {

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.editCapcity)
    EditText editCapcity;
    @BindView(R.id.editStartDate)
    EditText editStartDate;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editEndDateTime)
    EditText editEndDateTime;
    @BindView(R.id.editPrice)
    EditText editPrice;
    @BindView(R.id.editDescription)
    EditText editDescription;

    @BindView(R.id.tvSelectVenue)
    TextView tvSelectVenue;
    @BindView(R.id.tvBackArrow)
    TextView tvBackArrow;
    @BindView(R.id.tvAvailableTickets)
    TextView tvAvailableTickets;
    @BindView(R.id.rbPrivate)
    TextView rbPrivate;
    @BindView(R.id.rbPublic)
    TextView rbPublic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);

        initDrawer(true);
        init();
    }

    private void init() {
        initImageLoader();

        tvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEventActivity.super.onBackPressed();
            }
        });

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
//        if (editEmail.getText().toString().trim().length() <= 0) {
//            showToast(getString(R.string.err_email), Toast.LENGTH_SHORT);
//            return false;
//        }

//        if (!Patterns.EMAIL_ADDRESS.matcher(editLoginEmail.getText()).matches()) {
//            showToast(getString(R.string.err_email_invalid), Toast.LENGTH_SHORT);
//            return false;
//        }


        return true;
    }


}
