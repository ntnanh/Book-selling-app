package com.example.appbook.acitivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appbook.R;
import com.example.appbook.adapter.GiohangAdapter;
import com.example.appbook.model.EventBus.TinhTongEvent;
import com.example.appbook.ultil.CheckConnection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan, btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    long tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil(); //update total cart
        EvenButton();
        CactchOnItemListView();//delete product
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void EvenUltil() {
        tongtien = 0;
        for (int i = 0;i < HomeActivity.manggiohang.size();i++){
            tongtien += HomeActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+ ".000 VNĐ");
    }

    private void EvenButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeActivity.manggiohang.size() >0){
                    Intent intent = new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity(intent);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn đang rỗng");

                }
            }
        });
    }

    private void CactchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int positon, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xác nhận xoá sản phẩm");
                builder.setMessage("Bạn có chắc muốn xoá quyển sách này!!!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (HomeActivity.manggiohang.size() <=0){
                            txtthongbao.setVisibility(View.VISIBLE);

                        }else{
                            HomeActivity.manggiohang.remove(positon);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();

                            if (HomeActivity.manggiohang.size() <=0){
                                txtthongbao.setVisibility(View.VISIBLE);

                            }else
                            {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }




    private void CheckData() {
        if (HomeActivity.manggiohang.size() <=0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void Anhxa() {
        lvgiohang =(ListView) findViewById(R.id.listviewgiohang);
        txtthongbao = (TextView) findViewById(R.id.textviewthongbao);
        txttongtien = (TextView) findViewById(R.id.textviewtongtien);
        btntieptucmua = (Button) findViewById(R.id.buttontieptucmuahang);
        btnthanhtoan = (Button) findViewById(R.id.buttonthanhtoangiohang);
        toolbargiohang = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(CartActivity.this,HomeActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event){
        if (event !=null){
            EvenUltil();
        }
    }
}