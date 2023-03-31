package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ungdungdoctruyen.database.databasedoctruyen;

public class ManDangNhap extends AppCompatActivity {
//tạo biến cho màn đăng nhập
    EditText editTaiKhoan,editMatKhau;
    Button btnDangNhap,btnDangKy;
//tạo đối tượng cho databasedoctruyen
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        Anhxa();
        //Đối tượng databasedoctruyen
        databasedoctruyen = new databasedoctruyen(this);
        //Tạo sự kiện click button chuyên sang màn hình đăng ký với Intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManDangNhap.this,ManDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gán cho các biến là giá trị nhập vào từ editText
                String tentaikhoan = editTaiKhoan.getText().toString();
                String matkhau = editMatKhau.getText().toString();
            //sử dụng con trỏ để lấy dữ liệu,gọi tới getData()để lấy tất cả tải khoản ở database
                Cursor cursor = databasedoctruyen.getData();
                //thực hiện vòng lặp để lấy dữ liệu từ cursor với moveToNext() di chuyển từ tiếp
                while (cursor.moveToNext()){
                    //lấy dữ liệu và gán vào biến,dữ liệu tài khoản ở 1 và mat khau ở 2 , ô 0 là idtai khoan, ô 3 là email và 4 là phân quyền
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

//nếu tài khoản và mật khẩu nhập vào từ bàn phím khớp với ở database
                    if(datatentaikhoan.equals(tentaikhoan)&& datamatkhau.equals(matkhau)){
                        //Lấy dữ liệu phanquyen và id
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);

                        String tentk = cursor.getString(1);
//chuyển qua màn hình  main acitibity
                        Intent intent = new Intent(ManDangNhap.this,MainActivity.class);
//gửi dữ liệu qua activity mainactivyty
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);


                        startActivity(intent);

                    }

                }
                //thực hiện trả cursor
                cursor.moveToFirst();
                //đóng khi không dùng
                cursor.close();;

            }
        });
    }

    private void Anhxa() {
        editMatKhau = findViewById(R.id.matkhau);
        editTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy=findViewById(R.id.dangky);
        btnDangNhap=findViewById(R.id.dangnhap);
    }
}