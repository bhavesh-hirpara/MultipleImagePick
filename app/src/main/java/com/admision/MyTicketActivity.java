package com.admision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.admision.adapter.TicketsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTicketActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    TicketsAdapter TicketsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initBack();
        init();
    }

    private void init() {

        setTitleText("My Ticket");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        TicketsAdapter = new TicketsAdapter(this);
        mRecyclerView.setAdapter(TicketsAdapter);
    }
}
