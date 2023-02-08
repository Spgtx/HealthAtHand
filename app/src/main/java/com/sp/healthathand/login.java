package com.sp.healthathand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    Button callSignUp, login , forgotPass;

    ImageView image;

    TextView infor, logo_text;

    TextInputLayout username, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks
        callSignUp = findViewById(R.id.signup);

        forgotPass = findViewById(R.id.forgot_pass);
        image = findViewById(R.id.logo_image);
        infor = findViewById(R.id.slogan);
        logo_text = findViewById(R.id.logo_name);
        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);



        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,Signup.class);


                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View,String>(image, "logo_image");
                pairs[1] = new Pair<View,String>(logo_text, "logo_text" );
                pairs[2] = new Pair<View,String>(infor, "infor");
                pairs[3] = new Pair<View,String>(username, "username");
                pairs[4] = new Pair<View,String>(password, "password");
                pairs[5] = new Pair<View,String>(login, "login");
                pairs[6] = new Pair<View,String>(callSignUp, "no_acc");


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }
}