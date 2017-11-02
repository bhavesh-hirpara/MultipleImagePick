package com.admision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.admision.adapter.TicketsAdapter;
import com.admision.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketsActivity extends BaseActivity {

    @BindView(R.id.mTicketRecyclerView)
    RecyclerView mTicketRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    TicketsAdapter mTicketsAdapter;

    @BindView(R.id.tvBackArrow)
    TextView tvBackArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ButterKnife.bind(this);

//        initDrawer();
        init();
    }

    private void init() {
        initImageLoader();

        layoutManager = new LinearLayoutManager(this);
        mTicketRecyclerView.setLayoutManager(layoutManager);
        mTicketsAdapter = new TicketsAdapter(this);
        mTicketRecyclerView.setAdapter(mTicketsAdapter);

        tvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TicketsActivity.super.onBackPressed();
            }
        });

        mTicketsAdapter.setmEventlistener(new TicketsAdapter.Eventlistener() {
            @Override
            public void OnItemViewclick(int position, View view) {
//                Debug.e("View Details", "" + mAdapter.getItem(position).ID);
//                String id = mAdapter.getItem(position).ID;
//                Intent intent = new Intent(getActivity(),TicketsActivity.class);
//                startActivity(intent);
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
