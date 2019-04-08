package com.example.macbookpro.ttcn_tuyen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.macbookpro.ttcn_tuyen.Activity.HomeActivity;
import com.example.macbookpro.ttcn_tuyen.Activity.LoginActivity;

public class ScreamLogo extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scream_logo);
//        SharedPreferences.Editor editor = ghinho.edit();
//        editor.putString("tdn",nameuser);
//        editor.putString("mk",passuser);
//        editor.putBoolean("ckblogin",true);
//        editor.commit();
        sharedPreferences =getSharedPreferences("datalogin",MODE_PRIVATE);
//        edttenlogin.setText(ghinho.getString("level",""));
        final int level = sharedPreferences.getInt("level",-1);
//        edtmklogin.setText(ghinho.getString("mk",""));
//        ckblogin.setChecked(ghinho.getBoolean("ckblogin",false));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (level!=-1){
                    startActivity(new Intent(ScreamLogo.this,HomeActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(ScreamLogo.this,LoginActivity.class));
                    finish();
                }
            }
        },300);

    }
}
