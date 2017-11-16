package com.admision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.admision.adapter.SoldTicketAdapter;
import com.common.view.SimpleListDividerDecorator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SoldTicketActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    SoldTicketAdapter soldticketadapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_ticket);
        ButterKnife.bind(this);

        initBack();
        init();
    }

    private void init() {
        setTitleText("SOLD TICKETS");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
//        recyclerViewHost.setLayoutAnimation(Utils.getRowFadeSpeedAnimation(getActivity()));
        mRecyclerView.addItemDecoration(new SimpleListDividerDecorator(getResources().getDrawable(R.drawable.list_divider), true));
        soldticketadapter = new SoldTicketAdapter(this);
        mRecyclerView.setAdapter(soldticketadapter);
    }
}
