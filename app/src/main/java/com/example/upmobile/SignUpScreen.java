package com.example.upmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.upmobile.databinding.SingUpScreenBinding;
import com.example.upmobile.static_classies.Extensions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreen extends AppCompatActivity {
    SingUpScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = SingUpScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, SignInScreen.class));
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.email.length() != 0 || binding.password.length() != 0 || binding.fullName.length() != 0 || binding.phone.length() != 0){

                    if(!validation("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{1,3}$", binding.email.getText().toString())){
                        Extensions.AlertDialog("Оповещение", "Введите почту по маске name@domenname.ru", SignUpScreen.this);
                        return;
                    }

                    if(!validation("\"^\\+7\\s?[0-7|9]?[0-9]{2}\\s?[0-9]{3}\\s?[0-9]{2}\\s?[0-9]{2}\"", binding.phone.getText().toString())){
                        Extensions.AlertDialog("Оповещение", "Введите номер телефона по маске +7 ХХХ ХХХ ХХ ХХ", SignUpScreen.this);
                        return;
                    }

                    if(!validation("^[А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+$", binding.fullName.getText().toString())){
                        Extensions.AlertDialog("Оповещение", "Введите ФИО", SignUpScreen.this);
                        return;
                    }

                    if(binding.password.getText().toString().length() <= 6){
                        Extensions.AlertDialog("Оповещение", "Длина пароля должна быть больше 6 симворлов", SignUpScreen.this);
                        return;
                    }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email", binding.email.getText().toString());
                                        userInfo.put("fullName", binding.fullName.getText().toString());
                                        userInfo.put("phone", binding.phone.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);

                                        Extensions.AlertDialog("Оповещение", "Вы были успешно зарегистрированы", SignUpScreen.this);

                                        startActivity(new Intent(SignUpScreen.this, LaunchScreen.class));
                                        finish();
                                    }
                                }
                            });
                }
                else Extensions.AlertDialog("Оповещение", "Не все поля заполнены", SignUpScreen.this);
            }
        });
    }

    public boolean validation(String REGEX, String strValidate){
        Matcher match = Pattern.compile(REGEX).matcher(strValidate);
        return  match.matches();
    }
}
