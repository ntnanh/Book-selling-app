package com.example.appbook.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.appbook.R;
import com.example.appbook.model.Books;
import com.example.appbook.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailsProductActivity extends AppCompatActivity {

    ImageView Imganhsach;
    TextView Tvtensach, Tvgiasach, Tvtacgia, Tvctphanh, Tvngaysx, Tvnsx, Tvkthuoc, Tvdichgia, Tvloaibia, Tvsotrang;
    ReadMoreTextView readmota;
    Spinner spinnersoluong;
    Button Btngiohang;
    androidx.appcompat.widget.Toolbar toolbarchitiet;

    int id = 0;
    String Tensach ="";
    int Giasach = 0 ;
    String Imagesach = "";
    String Tacgia = "";
    String Mota = "";
    int Idtheloai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        Anhxa();
//        ActionToolbar();
        CatchEventSpinner();
        EventButton();
        GetInformation();
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

//    private void ActionToolbar() {
//        setSupportActionBar(toolbarchitiet);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }



    private void GetInformation() {
        Books books = (Books) getIntent().getSerializableExtra("Thongtinbooks");
        id = books.getID();
        Tensach = books.getTensach();
        Giasach = books.getGia();
        Mota = books.getGioithieusp();
        Imagesach = books.getImage();

        Tvtensach.setText(Tensach);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Tvgiasach.setText("Giá: "+ decimalFormat.format(Giasach)+".000 VNĐ");
        Tvtacgia.setText(Tacgia);
        readmota.setText(Mota);
        Picasso.with(getApplicationContext()).load(Imagesach)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(Imganhsach);
    }

    private void EventButton(){
        Btngiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeActivity.manggiohang.size() > 0){
                    int sl = Integer.parseInt(spinnersoluong.getSelectedItem().toString());
                    boolean exits = false;
                    for (int i = 0; i < HomeActivity.manggiohang.size();i++){
                        if (HomeActivity.manggiohang.get(i).getIdsp() == id){
                            HomeActivity.manggiohang.get(i).setSoluongsp(HomeActivity.manggiohang.get(i).getSoluongsp()+sl);
                            if (HomeActivity.manggiohang.get(i).getSoluongsp() >=10){
                                HomeActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            HomeActivity.manggiohang.get(i).setGiasp(Giasach * HomeActivity.manggiohang.get(i).getSoluongsp());
                            exits = true;
                        }
                    }
                    if (exits == false){
                        int soluong = Integer.parseInt(spinnersoluong.getSelectedItem().toString());
                        long Giamoi = soluong * Giasach;
                        HomeActivity.manggiohang.add(new Giohang(id,Tensach,Giamoi,Imagesach,soluong));
                    }
                }else{
                    int soluong = Integer.parseInt(spinnersoluong.getSelectedItem().toString());
                    long Giamoi = soluong * Giasach;
                    HomeActivity.manggiohang.add(new Giohang(id,Tensach,Giamoi,Imagesach,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });
        
    }

    private void CatchEventSpinner(){
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item,soluong);
        spinnersoluong.setAdapter(arrayAdapter);
    }
    private void Anhxa(){
        Imganhsach = findViewById(R.id.anhsach);
        Tvtensach = findViewById(R.id.tensach);
        Tvtacgia = findViewById(R.id.tacgia);
        Tvctphanh = findViewById(R.id.ctphanh);
        Tvngaysx = findViewById(R.id.ngaysx);
        Tvdichgia = findViewById(R.id.dichgia);
        Tvgiasach = findViewById(R.id.giasach);
        Tvkthuoc = findViewById(R.id.kthuoc);
        Tvloaibia = findViewById(R.id.loaibia);
        Tvsotrang = findViewById(R.id.strang);
        Tvnsx = findViewById(R.id.nsx);
        readmota = findViewById(R.id.mota);
        spinnersoluong = findViewById(R.id.soluong);
        Btngiohang = findViewById(R.id.giohang);
    }

}