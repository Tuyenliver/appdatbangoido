package com.example.macbookpro.ttcn_tuyen.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.macbookpro.ttcn_tuyen.Fragment.DrinkFragment;
import com.example.macbookpro.ttcn_tuyen.Fragment.FoodFragment;
import com.example.macbookpro.ttcn_tuyen.Fragment.HomeFragment;


public class MyViewPagerAdapter extends FragmentPagerAdapter {
    public static final int NUM_PAGER = 3;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance(0, "Trang Chủ");

            case 1:
                return DrinkFragment.newInstance(1, "Nước Uống");

            case 2:
                return FoodFragment.newInstance(2, "Đồ Ăn");

            default:
                return HomeFragment.newInstance(0, "Trang Chủ");

        }
    }

    @Override
    public int getCount() {
        return NUM_PAGER;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Trang Chủ";
            case 1:
                return "Nước Uống";
            case 2:
                return "Đồ Ăn";
            default:
                return "Trang Chủ";
        }
    }
}