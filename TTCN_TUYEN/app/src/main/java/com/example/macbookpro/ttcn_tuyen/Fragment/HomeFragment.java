package com.example.macbookpro.ttcn_tuyen.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_tuyen.Activity.HomeActivity;
import com.example.macbookpro.ttcn_tuyen.Activity.OrderActivity;
import com.example.macbookpro.ttcn_tuyen.Adapter.ChuaThanhToan;
import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterHoaDon;
import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterHome;
import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterList;
import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterorder;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Interface.SenData;
import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.Model.Order;
import com.example.macbookpro.ttcn_tuyen.Model.Sussec;
import com.example.macbookpro.ttcn_tuyen.Model.TableList;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private int mPage;
    private String mTitle;
    private TextView mText;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private EditText mEditText;
    private IDataAPI iDataAPI;
    private MyAdapterHome myAdapterHome;
    private ArrayList<TableList> tableList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    public static HomeFragment newInstance(int page, String title) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        homeFragment.setArguments(args);
        return homeFragment;
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//        mText = (TextView) rootView.findViewById(R.id.text);
        mEditText = rootView.findViewById(R.id.edittext_home);


        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mText.setText(mEditText.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyverView);

        iDataAPI =Common.getAPI();
        setrecyclerView();
    }

    private void setrecyclerView() {

        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        final android.app.AlertDialog dialog = new SpotsDialog.Builder().setContext(getContext()).setMessage("Xin cho").setCancelable(false).build();
        dialog.show();
        OrderActivity.compositeDisposable.add(iDataAPI.getdesk()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TableList>>() {
                    @Override
                    public void accept(List<TableList> ds) throws Exception {

                        if (tableList==null){
                            tableList = new ArrayList<>(ds.size());
                            myAdapterHome = new MyAdapterHome(getContext(),tableList,HomeFragment.this);
                            tableList.addAll(ds);
                        }
                        Log.d("DANGHIEU",ds.size()+"-"+tableList.size() );
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                    }
                })
        );

        recyclerView.setAdapter(myAdapterHome);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mText.setText(mTitle);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String text = mEditText.getText().toString().trim();
        if (text != null) {
            outState.putString("text", text);
        }
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            String text_old = savedInstanceState.getString("text");
            mEditText.setText(text_old);
        }
    }
    public void ThayDoi(final int id,final int id2){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hoadon);
        dialog.setCanceledOnTouchOutside(false);
        final RecyclerView recyclerViewhoadon =  (RecyclerView)dialog.findViewById(R.id.recyverViewhoadon);
        compositeDisposable.add(iDataAPI.gethoadonchuathanhtoan(id2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> orderList) throws Exception {

                        recyclerViewhoadon.setAdapter(new ChuaThanhToan(getContext(),orderList));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), "Loi ket noi", Toast.LENGTH_SHORT).show();

                    }
                })
        );
        recyclerViewhoadon.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerViewhoadon.setLayoutManager(linearLayoutManager);

        recyclerViewhoadon.addItemDecoration(new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation()));
//        recyclerViewhoadon.setAdapter(myAdapterHoaDon);
        int tong = 0;
//        for (Order or:Common.save_desk){
//            tong+=or.getGia()*or.getSoluong();
//        }
        dialog.findViewById(R.id.huyhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.chapnhanthanhtoanhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableList.get(id).setTrangthai(0);
                myAdapterHome.notifyDataSetChanged();
                OrderActivity.compositeDisposable.add(iDataAPI.update(id2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Sussec>>() {
                            @Override
                            public void accept(List<Sussec> ds) throws Exception {


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
                );
                dialog.cancel();
            }
        });
        dialog.show();


    }
    public void starOrder(){
        startActivityForResult(new Intent(getContext(),OrderActivity.class),2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            for (TableList tb:tableList){
                if (tb.getId()==data.getIntExtra("trangthai",-1)){
                    tb.setTrangthai(1);
                }
            }
            myAdapterHome.notifyDataSetChanged();


        }
    }
}
