package com.example.callprojectdz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.callprojectdz1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.btnCall.setOnClickListener(v -> call());
        initBtnBack();
    }

    private void initBtnBack() {
        binding.back.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }

    public void call() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]
                            {Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +
                    binding.edtNumber.getText()));
            startActivity(intent);
        }
    }
}