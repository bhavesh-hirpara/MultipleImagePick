package com.admision;

import android.app.Dialog;
import android.os.Bundle;

import butterknife.ButterKnife;

public class EventNameActivity extends BaseActivity {

//    @BindView(R.id.tvEventName)
//    TextView tvEventName;
//    @BindView(R.id.tvAge)
//    TextView tvAge;
//    @BindView(R.id.tvDate)
//    TextView tvDate;
//    @BindView(R.id.tvDate_)
//    TextView tvDate_;
//    @BindView(R.id.tvPrice)
//    TextView tvPrice;
//    @BindView(R.id.tvCity)
//    TextView tvCity;
//    @BindView(R.id.tvLocation)
//    TextView tvLocation;
//    @BindView(R.id.tvTimeSqure)
//    TextView tvTimeSqure;
//
//    @BindView(R.id.imgShare)
//    ImageView imgShare;
//
//    @BindView(R.id.btnBuyNow)
//    Button btnBuyNow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_name);
        ButterKnife.bind(this);
        
        init();
    }

    private void init() {
        
    }

    Dialog dialog;

//    public void showDialog() {
//
//        dialog = new Dialog(getActivity());
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.share_on_email);
//
//        rvListAttendedPopup = (RecyclerView) dialog.findViewById(R.id.rvListAttendedPopup);
//        TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
//        TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
//
//        layoutManager = new LinearLayoutManager(this);
//        rvListAttendedPopup.setLayoutManager(layoutManager);
//        mAdapter = new JournalAttendedPopupAdapter(this);
//        rvListAttendedPopup.setAdapter(mAdapter);
//
//        ArrayList<String> iDs = new ArrayList<>();
//        try {
//            iDs = Utils.asList(tvAttended.getTag().toString());
//        } catch (Exception e) {
//        }
//
//        ArrayList<Spinner> data = new ArrayList<>();
//        data.add(new Spinner("Support Group", "Support Group").setSelected(iDs.contains("Support Group")));
//        data.add(new Spinner("Professional Consultant", "Professional Consultant").setSelected(iDs.contains("Professional Consultant")));
//        data.add(new Spinner("Counseling", "Counseling").setSelected(iDs.contains("Counseling")));
//        data.add(new Spinner("Cognitive Behavior Therapy(CBT)", "Cognitive Behavior Therapy(CBT)").setSelected(iDs.contains("Cognitive Behavior Therapy(CBT)")));
//        data.add(new Spinner("Computerized Cognitive Behavior Therapy", "Computerized Cognitive Behavior Therapy").setSelected(iDs.contains("Computerized Cognitive Behavior Therapy")));
//        data.add(new Spinner("Psychotherapy", "Psychotherapy").setSelected(iDs.contains("Psychotherapy")));
//        data.add(new Spinner("Dialection Behavior Therapy(DBT)", "Dialection Behavior Therapy(DBT)").setSelected(iDs.contains("Dialection Behavior Therapy(DBT)")));
//        data.add(new Spinner("Psychiatry", "Psychiatry").setSelected(iDs.contains("Psychiatry")));
//
//        mAdapter.addAll(data);
//
//        mAdapter.setEventlistener(new JournalAttendedPopupAdapter.Eventlistener() {
//            @Override
//            public void onItemviewClick(int position) {
//                mAdapter.changeSelection(position, true);
//            }
//        });
//
//        tvOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                selectedId = mAdapter.getSelectedIds();
//                tvAttended.setText(selectedId);
//                tvAttended.setTag(selectedId);
//            }
//        });
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedId = "";
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
}
