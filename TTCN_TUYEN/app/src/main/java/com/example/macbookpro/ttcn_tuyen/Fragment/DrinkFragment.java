package com.example.macbookpro.ttcn_tuyen.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_tuyen.Activity.HomeActivity;
import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterList;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DrinkFragment extends Fragment {
    private int mPage;
    private String mTitle;
    private TextView mText;
    private RecyclerView recyclerView;
    private ArrayList<Danhsach> danhsaches;
    private IDataAPI iDataAPI;
    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        HomeActivity.compositeDisposable.clear();
        super.onStop();
    }

    public static DrinkFragment newInstance(int page, String title) {
        DrinkFragment settingFragment = new DrinkFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        settingFragment.setArguments(args);
        return settingFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page", 0);
        mTitle = getArguments().getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_drink, container, false);
        mText = (TextView) rootView.findViewById(R.id.text);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recuclerViewdrink);
        iDataAPI = Common.getAPI();
        setrecyclerView(2);
    }
    private void setrecyclerView(int loai) {
        danhsaches = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation()));
        final android.app.AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).setMessage("Xin cho").setCancelable(false).build();
        dialog.show();
        HomeActivity.compositeDisposable.add(iDataAPI.getDanhSachList(loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Danhsach>>() {
                    @Override
                    public void accept(List<Danhsach> ds) throws Exception {

                        recyclerView.setAdapter(new MyAdapterList(getContext(),ds));
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), "Loi ket noi", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
        );





        Log.d("SIZE",danhsaches.size()+"");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mText.setText(mTitle);
    }
}
