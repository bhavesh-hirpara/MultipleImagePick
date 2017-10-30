package com.admision;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.admision.adapter.SpinnerAdapter;
import com.admision.objects.Spinner;
import com.admision.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends BaseActivity {


    Handler handler = new Handler();

    @BindView(R.id.btnSignup)
    Button btnSignup;

    @BindView(R.id.editRegCompanyName)
    EditText editRegCompanyName;
    @BindView(R.id.editRegCountry)
    EditText editRegCountry;
    @BindView(R.id.editRegEmail)
    EditText editRegEmail;
    @BindView(R.id.editRegFirstName)
    EditText editRegFirstName;
    @BindView(R.id.editRegLastName)
    EditText editRegLastName;

    ArrayList<Spinner> busiType = new ArrayList<Spinner>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initImageLoader();

        editRegCountry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Spinner> conList = new ArrayList<Spinner>();
                conList.add(new Spinner("India", "India"));
                conList.add(new Spinner("USA", "USA"));

                showSpinner("" + getString(R.string.hint_country), editRegCountry, conList);
            }

        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {

                }

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
        if (editRegFirstName.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_firstname), Toast.LENGTH_SHORT);
            return false;
        }

        if (editRegLastName.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_lastname), Toast.LENGTH_SHORT);
            return false;
        }

        if (editRegCompanyName.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_company_name), Toast.LENGTH_SHORT);
            return false;
        }

        if (editRegEmail.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_email), Toast.LENGTH_SHORT);
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(editRegEmail.getText()).matches()) {
            showToast(getString(R.string.err_email_invalid), Toast.LENGTH_SHORT);
            return false;
        }

        if (editRegCountry.getText().toString().trim().length() <= 0) {
            showToast(getString(R.string.err_country), Toast.LENGTH_SHORT);
            return false;
        }

        return true;
    }

    private void showSpinner(String title, final TextView tv,
                             final ArrayList<Spinner> data) {

        final Dialog a = new Dialog(this);
        Window w = a.getWindow();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.spinner);
        w.setBackgroundDrawableResource(android.R.color.transparent);

        TextView lblselect = (TextView) w.findViewById(R.id.dialogtitle);
        lblselect.setTypeface(Utils.getBold(getActivity()));
        lblselect.setText(title.replace("*", "").trim());

        SpinnerAdapter adapter = new SpinnerAdapter(getActivity());
        adapter.setParentCategEnabled(false);
        ListView lv = (ListView) w.findViewById(R.id.lvSpinner);
        lv.setAdapter(adapter);
        adapter.addAll(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int position, long l) {

                tv.setText(data.get(position).title);
                tv.setTag(data.get(position).ID);

                a.dismiss();

            }
        });

        a.show();
    }


}
