package com.luminous.pick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.luminous.pick.Adapter.ImageListRecyclerAdapter;
import com.luminous.pick.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.imgSinglePick)
    ImageView imgSinglePick;

    @BindView(R.id.btnGalleryPick)
    Button btnGalleryPick;

    @BindView(R.id.btnGalleryPickMul)
    Button btnGalleryPickMul;

    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    String action;
    Handler handler;
    ImageListRecyclerAdapter imageListRecyclerAdapter;
    //	GalleryAdapter adapter;
    ImageLoader imageLoader;
    private HashMap<String, CustomGallery> imagesUri;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        initImageLoader();
        init();
    }

    public void initImageLoader() {

        imageLoader = Utils.initImageLoader(getActivity());

    }


    private void init() {

        handler = new Handler();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
//		gridGallery.setFastScrollEnabled(true);
        imageListRecyclerAdapter = new ImageListRecyclerAdapter(getApplicationContext());
        imageListRecyclerAdapter.setMultiplePick(false);
        recyclerView.setAdapter(imageListRecyclerAdapter);

        viewSwitcher.setDisplayedChild(1);

        btnGalleryPick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(Action.ACTION_PICK);
                startActivityForResult(i, 100);

            }
        });

//		btnGalleryPickMul = (Button) findViewById(R.id.btnGalleryPickMul);
        btnGalleryPickMul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
                startActivityForResult(i, 200);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageListRecyclerAdapter.clear();

            viewSwitcher.setDisplayedChild(1);
            String single_path = data.getStringExtra("single_path");
            imageLoader.displayImage("file://" + single_path, imgSinglePick);

        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");

            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

            for (String string : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;

                dataT.add(item);
            }

            viewSwitcher.setDisplayedChild(0);
            imageListRecyclerAdapter.addAll(dataT);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
