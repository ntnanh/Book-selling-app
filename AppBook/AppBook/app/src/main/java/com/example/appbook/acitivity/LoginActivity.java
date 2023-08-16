package com.example.appbook.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbook.R;
import com.example.appbook.retrofit.ApiBanHang;
import com.example.appbook.retrofit.RetrofitClient;
import com.example.appbook.ultil.MyApp;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    EditText emailEt, passwordEt;
    Button loginBtn;
    TextView noAccountTv;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        noAccountTv = findViewById(R.id.noAccountTv);
        progressBar = findViewById(R.id.progress);

        noAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String email, password;
                email = String.valueOf(emailEt.getText());
                password = String.valueOf(passwordEt.getText());

                if (!email.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.1.43/MyApp/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}