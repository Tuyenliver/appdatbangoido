package com.example.macbookpro.ttcn_tuyen.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.macbookpro.ttcn_tuyen.Adapter.MyViewPagerAdapter;
import com.example.macbookpro.ttcn_tuyen.Animation.DepthPageTransformer;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;

import io.reactivex.disposables.CompositeDisposable;

public class HomeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    FloatingActionButton floatingActionButton;


    IDataAPI iDataAPI;
    public static CompositeDisposable compositeDisposable = new CompositeDisposable();
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        mToolbar = findViewById(R.id.toolbar);
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager);
        floatingActionButton = findViewById(R.id.fab);
        if (Common.level==1){
            floatingActionButton.setVisibility(View.VISIBLE);
        }else {
            floatingActionButton.setVisibility(View.INVISIBLE);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop_menu();
            }
        });



//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("TabLayout ViewPager");

        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void setupViewPager() {
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("AAA",position+"");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("BBB",position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("CCC",state+"");
            }
        });
    }

    public void pop_menu(){
        PopupMenu popupMenu = new PopupMenu(HomeActivity.this,floatingActionButton);

        popupMenu.getMenuInflater().inflate(R.menu.menu_pop,popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dangxuat:{
                        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                        finish();
                        break;
                    }
                    case R.id.themnhanvien:{
                        startActivity(new Intent(HomeActivity.this,ThemNhanVien.class));
                        break;
                    }

                }
                return true;
            }
        });
        popupMenu.show();
    }
}
