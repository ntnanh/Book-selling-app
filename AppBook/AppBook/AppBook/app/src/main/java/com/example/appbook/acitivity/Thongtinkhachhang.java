package com.example.appbook.acitivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbook.R;
import com.example.appbook.ultil.CheckConnection;
import com.example.appbook.ultil.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Thongtinkhachhang extends AppCompatActivity {
    EditText edttenkhachhang, edtemail, edtsdt, edtdiachi;
    Button btnxacnhan, btnntrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        Anhxa();
        btnntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
        }
    }

    private void EventButton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edttenkhachhang.getText().toString().trim();
                String sdt = edtsdt.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String diachi = edtdiachi.getText().toString().trim();
                if (ten.length()>0 && sdt.length()>0 && email.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, MyApp.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse( final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if (Integer.parseInt(madonhang)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, MyApp.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            HomeActivity.manggiohang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn đã đặt hàn thành công!");
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(), "Mời bạn tiếp tục mua hàng!!");
                                        }else {
                                            CheckConnection.ShowToast_Short(getApplicationContext(), "Đặt hàng không thành công!!");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i<HomeActivity.manggiohang.size();i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",HomeActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",HomeActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",HomeActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",HomeActivity.manggiohang.get(i).getSoluongsp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            hashMap.put("diachi",diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu");
                }
            }
        });
    }

    private void Anhxa() {
        edttenkhachhang = (EditText) findViewById(R.id.edittexttenkhachhang);
        edtemail = (EditText) findViewById(R.id.edittextemail);
        edtsdt = (EditText) findViewById(R.id.edittextsodienthoai);
        edtdiachi = (EditText) findViewById(R.id.edittextdiachi);
        btnxacnhan = (Button) findViewById(R.id.butonxacnhan);
        btnntrove = (Button) findViewById(R.id.butontrove);

    }
}