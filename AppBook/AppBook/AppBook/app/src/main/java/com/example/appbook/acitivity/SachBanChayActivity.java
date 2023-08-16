package com.example.appbook.acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbook.R;
import com.example.appbook.adapter.SachbanchayAdapter;
import com.example.appbook.model.Books;
import com.example.appbook.ultil.CheckConnection;
import com.example.appbook.ultil.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SachBanChayActivity extends AppCompatActivity {
    Context context;
    androidx.appcompat.widget.Toolbar toolbarsbc;
    ListView lvsbc;
    SachbanchayAdapter sachbanchayAdapter;
    ArrayList<Books> mangsbc;
    int idsbc = 0;
    int page = 1;
    View footerview;
    boolean isLoading =false;
    boolean limiadata = false;
    mHandler mHandler;

    //tu them vao
//  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    // tu them vao
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            GetIdsachbanchay();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();}
    }

    private void LoadMoreData() {
        lvsbc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailsProductActivity.class);
                intent.putExtra("Thongtinbooks", mangsbc.get(i));
//                intent.putExtra("thongtinsanpham",mangsbc.get(i));
//                startActivity(intent);
                startActivity(intent);
            }
        });

        lvsbc.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount !=0 && isLoading == false && limiadata == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }

            }
        });
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

    private void GetData(int page) {
        RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());
        String duongdan = MyApp.Duongdansachbanchay+String.valueOf(page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id =0;
                String Tensbc = "";
                int Giasbc = 0;
                String Hinhanhsbc;
                String Mota = "";
                int Idsbc =0;
                if (response !=null && response.length() != 2){
                    lvsbc.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i=0 ; i< jsonArray.length();i++){
                            JSONObject jsonObject =jsonArray.getJSONObject(i);
                            id= jsonObject.getInt("id");
                            Tensbc = jsonObject.getString("tensach");
                            Giasbc = jsonObject.getInt("gia");
                            Hinhanhsbc = jsonObject.getString("image");
                            Mota = jsonObject.getString("gioithieusp");
                            Idsbc = jsonObject.getInt("idtheloai");
                            mangsbc.add(new Books(id, Tensbc, Giasbc, Hinhanhsbc, Mota, Idsbc));
                            sachbanchayAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limiadata = true;
                    lvsbc.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idtheloai",String.valueOf(idsbc));

                return param;
            }
        };
        requestQueue.add(stringRequest);


    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarsbc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsbc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbarsbc.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }
    private void GetIdsachbanchay() {
        idsbc = getIntent().getIntExtra("idloaibooks",-1);
        Log.d("giatritheloai", idsbc+"");
    }
    private void Anhxa() {
        toolbarsbc = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarsachbanchay);
        lvsbc = findViewById(R.id.listviewsachbanchay);
        mangsbc = new ArrayList<>();
        sachbanchayAdapter = new SachbanchayAdapter(getApplicationContext(),mangsbc);
        lvsbc.setAdapter(sachbanchayAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();


    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvsbc.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends  Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

}