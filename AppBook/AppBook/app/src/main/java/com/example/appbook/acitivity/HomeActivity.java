package com.example.appbook.acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbook.R;
import com.example.appbook.adapter.TheloaiAdapter;
import com.example.appbook.adapter.booksAdapter;
import com.example.appbook.databinding.ActivityHomeBinding;
import com.example.appbook.databinding.ActivityMainBinding;
import com.example.appbook.model.Books;
import com.example.appbook.model.Giohang;
import com.example.appbook.model.Theloai;
import com.example.appbook.ultil.CheckConnection;
import com.example.appbook.ultil.MyApp;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    //private ActivityHomeBinding binding;
    Toolbar  toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Theloai> mangtheloai;
    TheloaiAdapter theloaiAdapter;
    ArrayList<Books> mangsanpham;
    booksAdapter booksAdapter;
    public static ArrayList<Giohang> manggiohang;


    int  id = 0;
    String tentheloai = "";
    String hinhanhtheloai = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Anhxa();
//        ActionBar();
//            ActionViewFlipper();

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){

            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoiNhat();
             CatchOnItemListView();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i){
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(HomeActivity.this, SachBanChayActivity.class);
                            intent.putExtra("idloaibooks", mangtheloai.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(HomeActivity.this, SachmoiActivity.class);
                            intent.putExtra("idloaibooks", mangtheloai.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

            }
        });

    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new  JsonArrayRequest(MyApp.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response !=null){
                    int ID = 0;
                    String Tensach ="";
                    Integer gia =0;
                    String Image ="";
                    String Gioithieusp = "";
                    int IDtheloai = 0;
                    for (int i =0 ; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tensach = jsonObject.getString("tensach");
                            gia = jsonObject.getInt("gia");
                            Image = jsonObject.getString("image");
                            Gioithieusp = jsonObject.getString("gioithieusp");
                            IDtheloai = jsonObject.getInt("idtheloai");
                            mangsanpham.add(new Books(ID, Tensach, gia, Image, Gioithieusp, IDtheloai));
                            booksAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }



                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void GetDuLieuLoaisp() {
        //đọc dữ liệu json bằng volley
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(MyApp.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null ){
                    for (int i = 0 ; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tentheloai = jsonObject.getString("tentheloai");
//                            tentheloai = jsonObject.getString("tenloaisp");
                            hinhanhtheloai = jsonObject.getString("hinhanhtheloai");
                            mangtheloai.add(new Theloai(id, tentheloai, hinhanhtheloai));
                            theloaiAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangtheloai.add(7, new Theloai(0, "THÔNG TIN","http://ngochieu.name.vn/img/home.png"));
//                    mangtheloai.add(4, new Theloai(0, "THÔNG TIN","https://image.flaticon.com/icons/png/512/943/943579.png"));


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    //bắt sự kiện chạy quảng cáo
    private void ActionViewFlipper() {
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/McBooksT12_840x320.jpg");
        quangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/Branday_mainbanner_Slide_840x320.jpg");
        quangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/TanVietT12_840x320.jpg  ");
        quangcao.add("https://cdn0.fahasa.com/media/magentothem/banner7/Trang_Manga-Comic_Mainbanner_T10_Slide_840x320.jpg");
        for (int i = 0 ; i<quangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);


        }
        viewFlipper.setFlipInterval(5000); //chạy trong 5s
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    //bắt sự kiện toolbar
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    private void Anhxa() {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listViewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        mangtheloai = new ArrayList<>();
        mangtheloai.add(0, new Theloai(0, "TRANG CHỦ","http://ngochieu.name.vn/img/home.png"));
        theloaiAdapter = new TheloaiAdapter(mangtheloai, getApplicationContext());
        listViewmanhinhchinh.setAdapter(theloaiAdapter);
        mangsanpham = new ArrayList<>();
        booksAdapter = new booksAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(booksAdapter);
        if (manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }
}