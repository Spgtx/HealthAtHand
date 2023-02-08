package com.sp.healthathand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhone_no, regPassword;
    Button callLogin, signup;


    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        callLogin = findViewById(R.id.bck_login);
        signup = findViewById(R.id.signup);
        callLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Signup.this, login.class);
            startActivity(intent);
        });

        /*
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, dashboard.class);
                startActivity(intent);
            }
        });
*/

    }
}