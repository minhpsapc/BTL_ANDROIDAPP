package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ManThongTin extends AppCompatActivity {

    TextView txtThongtinapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_thong_tin);

        txtThongtinapp = findViewById(R.id.textviewthongtin);
        String thongtin = "ứng dụng được phát hành bởi 'Khánh Jos'\n"+
                "Uy tín đạt 100%\n"+
                "Ok";

        txtThongtinapp.setText(thongtin);
    }
}