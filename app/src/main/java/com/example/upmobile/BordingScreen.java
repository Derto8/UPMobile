package com.example.upmobile;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.upmobile.databinding.OnBoardingScreenBinding;

public class BordingScreen extends AppCompatActivity {
    private OnBoardingScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OnBoardingScreenBinding.inflate(getLayoutInflater());
        setContentView(R.layout.on_boarding_screen_step_1);
    }

    int start = 0;
    int end = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                start = (int) event.getX();
                break;
            case MotionEvent.ACTION_DOWN:
                end = (int) event.getX();
                break;
        }
        if(start != 0 && end != 0)
        {

            if(start > end) setContentView((R.layout.on_boarding_screen_step_1));

            else if(start < end)
            {
                setContentView(R.layout.on_boarding_screen_step_2);
                Button sign_in = findViewById(R.id.btnSignIn);
                Button sign_up = findViewById(R.id.btnSignUp);
                TextView goust = findViewById(R.id.goust);
                if (isInternetAvailable(getApplicationContext())) {
                    goust.setVisibility(View.GONE);

                } else {
                    goust.setVisibility(View.VISIBLE);
                }
                goust.setOnClickListener(v -> {
                    startActivity(new Intent(BordingScreen.this, LaunchScreen.class));
                });
                sign_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(BordingScreen.this,SignInScreen.class));
                    }
                });
                sign_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(BordingScreen.this,SignUpScreen.class));
                    }
                });

            }
            start = 0;
            end = 0;
        }
        return false;
    }

    public boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo.isConnected())
            return true;
        return false;
    }
}
