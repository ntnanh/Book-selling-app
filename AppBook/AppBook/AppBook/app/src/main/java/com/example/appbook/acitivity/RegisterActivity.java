package com.example.appbook.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appbook.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class RegisterActivity extends AppCompatActivity {

    EditText usernameEt, emailEt, phoneEt, passwordEt;
    Button registBtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEt = findViewById(R.id.username);
        emailEt = findViewById(R.id.email);
        phoneEt = findViewById(R.id.phone);
        passwordEt = findViewById(R.id.password);
        registBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progress);

        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, email, phone, password;
                username = String.valueOf(usernameEt.getText());
                email = String.valueOf(emailEt.getText());
                phone = String.valueOf(phoneEt.getText());
                password = String.valueOf(passwordEt.getText());

                if (!username.equals("") && !email.equals("") && !phone.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "username";
                            field[1] = "email";
                            field[2] = "phone";
                            field[3] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = username;
                            data[1] = email;
                            data[2] = phone;
                            data[3] = password;
                            PutData putData = new PutData("http://192.168.1.6/MyApp/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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