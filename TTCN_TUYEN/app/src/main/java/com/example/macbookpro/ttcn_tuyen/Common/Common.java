package com.example.macbookpro.ttcn_tuyen.Common;


import com.example.macbookpro.ttcn_tuyen.Model.Order;
import com.example.macbookpro.ttcn_tuyen.Model.TableList;
import com.example.macbookpro.ttcn_tuyen.Retrofit.IDataAPI;
import com.example.macbookpro.ttcn_tuyen.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {


    public static TableList select_table;
    public static String tukhoa;

    public static ArrayList<Order> save_desk;
    public static int level;

    public  static IDataAPI getAPI(){
        return RetrofitClient.getInstance().create(IDataAPI.class);
    }
}
