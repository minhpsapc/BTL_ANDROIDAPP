package com.example.ungdungdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.ungdungdoctruyen.adapter.adapterTruyen;
import com.example.ungdungdoctruyen.adapter.adapterchuyenmuc;
import com.example.ungdungdoctruyen.adapter.adapterthongtin;
import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.TaiKhoan;
import com.example.ungdungdoctruyen.model.Truyen;
import com.example.ungdungdoctruyen.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    //b8_Listview truyện mới
    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;

    adapterTruyen adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);
        //b8_Listview truyện mới //nhận dữ liệu ở màn đăng nhập gửi
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq", 0);
        int idd = intentpq.getIntExtra("idd", 0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");
        AnhXa();
        ActionBar();
        ActionViewFlipper();

        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ManNoiDung.class);

                String tent = TruyenArraylist.get(position).getTenTruyen();
                String noidungt = TruyenArraylist.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

        //9_bắt click item cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    if(i==2){

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài:", "Bạn không có quyền");
                    }
                }
                else if(i==1){


                }
                else if(i==2){
                    finish();
                }

            }
        });

    }
    //thanh actionbar với toolbar
private void ActionBar(){
        //Hàm tạo hỗ trợ toolbar
        setSupportActionBar(toolbar);
//set nút cho actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
//Tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
//bắt sự kiên click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

}
//Phương thức cho chạy quảng cáo với ViewFlipper
    private void ActionViewFlipper() {
        //mảng chữ ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
//Add anhe vào mảng
        mangquangcao.add("https://lh3.googleusercontent.com/p/AF1QipNNMbETsHCu-WI7MO0uI0mUYQo6AZYSLa1JIC87=s1360-w1360-h1020");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");

        //Thực hiện vòng lặp for gán ảnh vào Imageview rồi từ imageview lên app
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            //Sử dụng hàm thư viện pisscsso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //phương thức chỉnh tấm hỉnh vừa khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //thêm ảnh từ imageview vào viewlipper
            viewFlipper.addView(imageView);

        }
        //thiết lập tự động chạy trong 4s
        viewFlipper.setFlipInterval(4000);
//run viewfliper
        viewFlipper.setAutoStart(true);

        //gọi amination cho vào và ra
        Animation animation_slide_inn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        //Gọi am=nimation vào viewlipper
        viewFlipper.setInAnimation(animation_slide_inn);
        viewFlipper.setInAnimation(animation_slide_out);
    }
    //Phương thức ánh xạ
    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin=findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);
        //b8
        TruyenArraylist = new ArrayList<>();

        Cursor cursor1 = databasedoctruyen.getDatal();
        while(cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        //9_Thông tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

        adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        //9_chuyên mục
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.baseline_post_add_24)  );
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.baseline_login_24));

        adapterchuyenmuc = new adapterchuyenmuc(this, R.layout.chuyenmuc, chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);

    }
//Nhập một menu tìm kiếm vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //nếu click vào nút icon tk sẽ chuyển qua màn hình tìm kiếm
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}