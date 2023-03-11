package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnsignup,btnlogin;
    //private FrameLayout framelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectComponents();
    }

    private void connectComponents() {

        btnsignup = findViewById(R.id.btnSignupMain);
        btnlogin = findViewById(R.id.btnLoginMain);
        //framelay = findViewById(R.id.FrameLayoutMain);
    }


    public void LoginFragment(View view) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new LogInFragment());
        ft.commit();
    }

    public void SignupFragment(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new SignUpFragment());
        ft.commit();
    }


}