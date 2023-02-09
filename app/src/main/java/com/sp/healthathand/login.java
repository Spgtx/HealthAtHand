package com.sp.healthathand;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

    private Boolean ValidateUsername (){
        String val = username.getEditText().getText().toString();

        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidatePassword(){
        String val = password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }
    }

    private void loginUser(View view){
        if(!ValidateUsername() | !ValidatePassword()){
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {
        String UserEnteredUsername = username.getEditText().getText().toString().trim();
        String UserEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(UserEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(UserEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(UserEnteredPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String emailFromDB = snapshot.child(UserEnteredUsername).child("email").getValue(String.class);
                        String nameFromDB = snapshot.child(UserEnteredUsername).child("name").getValue(String.class);
                        String phone_noFromDB = snapshot.child(UserEnteredUsername).child("phone_no").getValue(String.class);
                        String usernameFromDB = snapshot.child(UserEnteredUsername).child("username").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phone_no", phone_noFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    }
                    else {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);

            }
        });
    }




}