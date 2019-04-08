package com.example.macbookpro.ttcn_tuyen.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcn_tuyen.Activity.OrderActivity;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Interface.SenData;
import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.Model.Order;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FragmentShow extends Fragment {

    ImageView imageView;
    TextView textViewten,textViewthanhtien;
    EditText editTextsl;
    Danhsach dss;
    IDataAPI iDataAPI;
    Button buttonhuy,buttongoithem,buttonthanhtoan;
    SenData senData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_order,container,false);
    }

    @Override
    public void onStop() {
        OrderActivity.compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) view.findViewById(R.id.imgmon);
        textViewten = (TextView) view.findViewById(R.id.tenmon);
        textViewthanhtien = (TextView) view.findViewById(R.id.thanhtienmonan);
        editTextsl = (EditText) view.findViewById(R.id.slmon);
        buttonhuy = (Button) view.findViewById(R.id.huyorder);
        buttongoithem = (Button) view.findViewById(R.id.goithemorder);
        buttonthanhtoan = (Button) view.findViewById(R.id.themorder);
        iDataAPI =Common.getAPI();
        fetchMonAn(Common.tukhoa);
        buttonhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senData.click();
            }
        });
        buttongoithem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dss!=null){
                    senData.sent(dss,Integer.parseInt(editTextsl.getText().toString().trim()));
                }
            }
        });

    }
    private void fetchMonAn(String tukhoa) {
        final android.app.AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).setMessage("Xin cho").setCancelable(false).build();
        dialog.show();
        OrderActivity.compositeDisposable.add(iDataAPI.getSearchhList(tukhoa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Danhsach>>() {
                    @Override
                    public void accept(List<Danhsach> ds) throws Exception {
                         dss = ds.get(0);
                        Picasso.get().load("https://s8.mkklcdnv8.com/mangakakalot/d2/doraemon/vol0_chapter_11/2.jpg").into(imageView);
                        textViewten.setText(dss.getTen());

                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                    }
                })
        );

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        senData = (SenData) getActivity();
    }
}
