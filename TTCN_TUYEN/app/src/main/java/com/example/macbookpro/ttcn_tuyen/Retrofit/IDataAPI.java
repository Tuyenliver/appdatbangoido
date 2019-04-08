package com.example.macbookpro.ttcn_tuyen.Retrofit;

import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.Model.Order;
import com.example.macbookpro.ttcn_tuyen.Model.Sussec;
import com.example.macbookpro.ttcn_tuyen.Model.TableList;
import com.example.macbookpro.ttcn_tuyen.Model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDataAPI {

    @GET("Model/get_table_sp.php")
    Observable<List<Danhsach>> getDanhSachList(@Query("loaisp") int loai);

    @GET("Model/get_table_sp.php")
    Observable<List<Danhsach>> getSearchhList(@Query("search") String search);

    @GET("Model/inserHoadon.php")
    Observable<List<Sussec>> postHoaDon(@Query("data") String data);

    @GET("Model/get_user.php")
    Observable<List<User>> getdangnhap(@Query("user") String user);

    @GET("Model/inseruser.php")
    Observable<List<User>> themuser(@Query("data") String user);

    @GET("Model/get_hoadon_chua_tt.php")
    Observable<List<Order>> gethoadonchuathanhtoan(@Query("iddesk") int idesk);

    @GET("Model/updatedesk.php")
    Observable<List<Sussec>> update(@Query("id") int id);

    @GET("MODEL/get_desk.php")
    Observable<List<TableList>> getdesk();
}
