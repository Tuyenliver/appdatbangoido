package com.example.macbookpro.ttcn_tuyen.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.macbookpro.ttcn_tuyen.Adapter.MyAdapterList;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.Model.Sussec;
import com.example.macbookpro.ttcn_tuyen.Model.User;
import com.example.macbookpro.ttcn_tuyen.R;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {
    EditText editText1,editText2;
    IDataAPI dataAPI;
    ArrayList<userjson> userjsonArrayList = new ArrayList<>(1);
    ArrayList<User> userArrayList = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    SharedPreferences sharedPreferences;
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            editText1 = findViewById(R.id.tendangnhap);
            editText2 = findViewById(R.id.passdangnhap);
            dataAPI = Common.getAPI();




        findViewById(R.id.btndangnhap).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userjsonArrayList.clear();
                userjsonArrayList.add(new userjson(editText1.getText().toString(),editText2.getText().toString()));
                Gson gson = new Gson();

                final String mang = gson.toJson(userjsonArrayList);
                Log.d("MANG",mang);
                fetchuser(mang);

            }
        });

    }

    private void fetchuser(String mang) {
        compositeDisposable.add(dataAPI.getdangnhap(mang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> ds) throws Exception {
                        userArrayList.clear();
                        userArrayList.addAll(ds);
                        if(ds.size()>0){
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            Common.level =Integer.parseInt(ds.get(0).getLevel());
                            finish();
                            Toast.makeText(LoginActivity.this, "hahaha"+ds.get(0).getHoten(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Ten dang nhap hoac mat khau khogn chinh xac", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(LoginActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("ERRO",throwable.getMessage());

                    }
                })
        );
    }

    private void hihi(String a,String b){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("level",Integer.parseInt(a));
        editor.putString("hoten",b);
        editor.commit();
    }
}
class userjson{
    String tdn,pass;

    public userjson(String tdn, String pass) {
        this.tdn = tdn;
        this.pass = pass;
    }
}

