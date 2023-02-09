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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhone_no = findViewById(R.id.phone_no);
        regPassword = findViewById(R.id.password);
        callLogin = findViewById(R.id.bck_login);
        signup = findViewById(R.id.signup);

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, login.class);
                startActivity(intent);
            }
        });

    }


    //validation
    private Boolean ValidateName(){
        String val = regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()<5){
            regUsername.setError("Username is too Short");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidateUsername (){
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w\\z";

        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>= 15){
            regUsername.setError("Username is too long");
            return false;
        }
        else if(val.length()<5){
            regUsername.setError("Username is too Short");
            return false;
        }
        else if (!val.matches(noWhiteSpace)){
            regUsername.setError("White spaces not allowed");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email format");
            return false;
        }
        else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidatePhone_no(){
        String val = regPhone_no.getEditText().getText().toString();
        if(val.isEmpty()){
            regPhone_no.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>10){
            regPhone_no.setError("Phone no is too long");
            return false;
        }
        else if(val.length()< 10){
            regPhone_no.setError("Phone no is too short");
            return false;
        }
        else {
            regPhone_no.setError(null);
            regPhone_no.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidatePassword(){
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordVal)){
            regPassword.setError("Password is too weak");
            return false;
        }
        else {
            regPassword.setError(null);
            regPassword.setEnabled(false);
            return true;
        }
    }


    //save data to firebase button click
    public void registerUser(View view){


        if (!ValidateName() | !ValidateUsername() | !ValidateEmail() | !ValidatePhone_no() | !ValidatePassword()){
            return;
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Get all the values in string
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phone_no = regPhone_no.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, phone_no, password);

                reference.child(phone_no).setValue(helperClass);


            }
        });


    }




}
