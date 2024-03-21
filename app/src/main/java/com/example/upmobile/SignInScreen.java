package com.example.upmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.upmobile.databinding.SignInScreenBinding;
import com.example.upmobile.static_classies.Extensions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInScreen extends AppCompatActivity {

    SignInScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(binding.password.length() != 0 && binding.email.length() != 0){
                    Matcher match = Pattern.compile("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{1,3}$").matcher(binding.email.getText().toString());
                    if(match.matches()){
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(SignInScreen.this, LaunchScreen.class));
                                            finish();
                                        } else
                                            Toast.makeText(v.getContext(), "Польватель не найден", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else Extensions.AlertDialog("Оповещение", "Неверно введена почта!", SignInScreen.this);
                }
                else
                    Extensions.AlertDialog("Оповещение", "Не все поля запонены", SignInScreen.this);
            }
        });
    }
}