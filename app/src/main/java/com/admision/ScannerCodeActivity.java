package com.admision;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.admision.adapter.ScannerCodeAdapter;
import com.common.view.SimpleListDividerDecorator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScannerCodeActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ScannerCodeAdapter scannerCodeAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_code);
        ButterKnife.bind(this);

        initBack();
        init();
    }

    private void init() {
         setTitleText("SCANNER CODE");

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
//        recyclerViewHost.setLayoutAnimation(Utils.getRowFadeSpeedAnimation(getActivity()));
        mRecyclerView.addItemDecoration(new SimpleListDividerDecorator(getResources().getDrawable(R.drawable.list_divider), true));
        scannerCodeAdapter = new ScannerCodeAdapter(this);
        mRecyclerView.setAdapter(scannerCodeAdapter);
    }
}
