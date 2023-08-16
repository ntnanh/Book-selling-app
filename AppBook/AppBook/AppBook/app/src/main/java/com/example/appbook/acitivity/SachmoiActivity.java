package com.example.appbook.acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbook.R;
import com.example.appbook.adapter.SachmoiAdapter;
import com.example.appbook.model.Books;
import com.example.appbook.ultil.CheckConnection;
import com.example.appbook.ultil.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SachmoiActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarsm;
    ListView lvsm;
    SachmoiAdapter sachmoiAdapter;
    ArrayList<Books> mangsm;
    int idsm = 0;
    int page = 1;
    View footerview;
    boolean isLoading =false;
    boolean limiadata = false;
    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachmoi);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            GetIdsachmoi();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();}

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
    private void LoadMoreData() {
        lvsm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailsProductActivity.class);
                intent.putExtra("Thongtinbooks", mangsm.get(i));
//                intent.putExtra("thongtinsanpham",mangsbc.get(i));
//                startActivity(intent);
                startActivity(intent);
            }
        });

        lvsm.setOnScrollListener(new AbsListView.OnScrollListener() {
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

    private void GetIdsachmoi() {
        idsm = getIntent().getIntExtra("idloaibooks",-1);
    }

    private void Anhxa() {
        toolbarsm = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarsachmoi);
        lvsm = findViewById(R.id.listviewsachmoi);
        mangsm = new ArrayList<>();
        sachmoiAdapter = new SachmoiAdapter(getApplicationContext(),mangsm);
        lvsm.setAdapter(sachmoiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarsm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsm.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
    private void GetData(int page) {
        RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());
        String duongdan = MyApp.Duongdansachbanchay+String.valueOf(page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id =0;
                String Tensm = "";
                int Giasm = 0;
                String Hinhanhsm;
                String Motasm = "";
                int Idsm =0;
                if (response !=null && response.length() != 2){
                    lvsm.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i=0 ; i< jsonArray.length();i++){
                            JSONObject jsonObject =jsonArray.getJSONObject(i);
                            id= jsonObject.getInt("id");
                            Tensm = jsonObject.getString("tensach");
                            Giasm = jsonObject.getInt("gia");
                            Hinhanhsm = jsonObject.getString("image");
                            Motasm = jsonObject.getString("gioithieusp");
                            Idsm = jsonObject.getInt("idtheloai");
                            mangsm.add(new Books(id, Tensm, Giasm, Hinhanhsm, Motasm, Idsm));
                            sachmoiAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limiadata = true;
                    lvsm.removeFooterView(footerview);
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
                param.put("idtheloai",String.valueOf(idsm));

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvsm.addFooterView(footerview);
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