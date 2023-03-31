package com.example.ungdungdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ungdungdoctruyen.R;
import com.example.ungdungdoctruyen.model.TaiKhoan;

import java.util.List;

public class adapterthongtin extends BaseAdapter {

    private Context context;
    private int layout;

    private List<TaiKhoan> taiKhoanList;

    public adapterthongtin(Context context, int layout, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }


    @Override
    public int getCount() {
        return taiKhoanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtTenTaiKhoan = (TextView) view.findViewById(R.id.TEXT_NAME);
        TextView txtGmail = (TextView) view.findViewById(R.id.TEXT_Gmail);

        TaiKhoan taiKhoan = taiKhoanList.get(i);

        txtTenTaiKhoan.setText(taiKhoan.getmTenTaiKhoan());
        txtGmail.setText(taiKhoan.getmEmail());


        return view;
    }
}
