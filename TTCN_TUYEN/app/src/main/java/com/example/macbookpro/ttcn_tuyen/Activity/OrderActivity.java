package com.example.macbookpro.ttcn_tuyen.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterHoaDon;
import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterorder;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Fragment.FragmentShow;
import com.example.macbookpro.ttcn_tuyen.Interface.SenData;
import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.Model.Order;
import com.example.macbookpro.ttcn_tuyen.Model.Sussec;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity implements SenData {
    TextView textView;
    public static ArrayList<Order> list;
    RecyclerView recyclerView2;
    public static MyAdapterorder myAdapterorder;
    MyAdapterHoaDon myAdapterHoaDon;
    EditText editText;
    IDataAPI iDataAPI;
    public static CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        textView = (TextView) findViewById(R.id.textsoban) ;
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerorder);
        editText = (EditText) findViewById(R.id.timkiemorrder);
        textView.setText(new StringBuilder().append("Bàn số ").append(Common.select_table.getSoban()));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count==0){
                    getSupportFragmentManager().beginTransaction().remove(new FragmentShow()).commit();
                }else {
                    Common.tukhoa= editText.getText().toString().trim();
                    getSupportFragmentManager().beginTransaction().replace(R.id.showmon,new FragmentShow()).commitAllowingStateLoss();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        list = new ArrayList<>();
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager);
         myAdapterorder= new MyAdapterorder(OrderActivity.this,list);
        recyclerView2.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));
        recyclerView2.setAdapter(myAdapterorder);
        iDataAPI = Common.getAPI();


    }
    private void ShowHoaDon(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hoadon);
        dialog.setCanceledOnTouchOutside(false);
        RecyclerView recyclerViewhoadon =  (RecyclerView)dialog.findViewById(R.id.recyverViewhoadon);
        myAdapterHoaDon = new MyAdapterHoaDon(this,list);

        recyclerViewhoadon.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerViewhoadon.setLayoutManager(linearLayoutManager);

        recyclerViewhoadon.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));
        recyclerViewhoadon.setAdapter(myAdapterHoaDon);
        int tong = 0;
        for (Order or:list){
            tong+=or.getGia()*or.getSoluong();
        }
        dialog.findViewById(R.id.huyhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.chapnhanthanhtoanhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String datatoJson = gson.toJson(list);
                Common.save_desk =new ArrayList<>(list.size());
                Common.save_desk.addAll(list);

                Intent i = new Intent();
                i.putExtra("trangthai", Common.select_table.getId());

                setResult(RESULT_OK, i);
                finish();
                Log.d("ARRAY",datatoJson);
                dialog.cancel();
                OrderActivity.compositeDisposable.add(iDataAPI.postHoaDon(datatoJson)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Sussec>>() {
                            @Override
                            public void accept(List<Sussec> ss) throws Exception {

//[{"thanhcong":"sussec"}]
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
                );

            }
        });



        dialog.show();
    }
    public void removeById(int id){
        list.remove(id);
        myAdapterorder.notifyDataSetChanged();
    }

    @Override
    public void sent(Danhsach ds,int sl) {
        int gia=Integer.parseInt(ds.getGia());
        int issp= Integer.parseInt(ds.getId());
        if(list.size()==0){
            list.add(new Order(ds.getTen(),gia,sl,0,Common.select_table.getId(),1,issp));
        }else {
            int dem=0;
            for (int i=0;i<list.size();i++){
                if (list.get(i).getTen().equals(ds.getTen())){
                    list.get(i).setSoluong(list.get(i).getSoluong()+sl);
                    dem=1;
                }
            }
            if (dem==0){
                list.add(new Order(ds.getTen(),gia,sl,0,Common.select_table.getId(),1,issp));

            }
        }
        myAdapterorder.notifyDataSetChanged();
    }

    @Override
    public void click() {
        ShowHoaDon();
    }
}
