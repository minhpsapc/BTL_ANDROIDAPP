package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.TaiKhoan;

public class ManDangKy extends AppCompatActivity {
EditText editDKTaiKhoan, editMatKhau , editEmail;
Button btnDKDangKy,btnDKDangNhap;

databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);
        databasedoctruyen = new databasedoctruyen(this);
        AnhXa();
        //click cho button đăng ký
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = editDKTaiKhoan.getText().toString();
                String matkhau = editMatKhau.getText().toString();
                String email = editEmail.getText().toString();


                TaiKhoan taiKhoan1 = CreateTaiKhoan();
                if (taikhoan.equals("")|| matkhau.equals("")||email.equals("")){
                    Log.e("Thông báo: ","Chưa nhập đầy đủ thông tin");
                }
                //nếu đầy đủ thông tin nhập vào thì add tài khoản vào database
                else {
                       databasedoctruyen.AddTaiKhoan(taiKhoan1);
                    Toast.makeText(ManDangKy.this,"Chúc mừng bạn đã đăng ký thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
        //trở về đnhap
btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
    }
    //phg thưc khơi tạo tài khoản
private TaiKhoan CreateTaiKhoan(){
        String taikhoan = editDKTaiKhoan.getText().toString();
        String matkhau = editMatKhau.getText().toString();
        String email = editEmail.getText().toString();
        int phanquyen = 1;
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);

        return tk;
}
    private void AnhXa() {
        editEmail = findViewById(R.id.dkemail);
        editMatKhau = findViewById(R.id.dkmatkhau);
        editDKTaiKhoan = findViewById(R.id.dktaikhoan);
        btnDKDangKy = findViewById(R.id.dkdangky);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);

    }
}