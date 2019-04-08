package com.example.macbookpro.ttcn_tuyen.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Model.User;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThemNhanVien extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText editText1,editText2,editText3;
    IDataAPI dataAPI ;
    ArrayList<User> arrayList = new ArrayList<>();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        editText1 = findViewById(R.id.addtdn);
        editText2 = findViewById(R.id.addhoten);
        editText3 = findViewById(R.id.addpass);
        dataAPI = Common.getAPI();
        findViewById(R.id.dangkyuser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                arrayList.add(new User("1",editText1.getText().toString(),editText3.getText().toString(),"2",editText2.getText().toString()));
                Gson gson = new Gson();

                String hihi = gson.toJson(arrayList);

                compositeDisposable.add(dataAPI.themuser(hihi)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<User>>() {
                            @Override
                            public void accept(List<User> ds) throws Exception {
                               finish();
                                Toast.makeText(ThemNhanVien.this, "thanh cong"+ds.get(0).getHoten(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ThemNhanVien.this, "ten dang nhap hoac mk k chinh xac"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("ERRO",throwable.getMessage());

                            }
                        })
                );
            }
        });
    }
}
