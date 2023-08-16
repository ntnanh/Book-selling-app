package com.example.appbook.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbook.R;
import com.example.appbook.model.Books;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SachbanchayAdapter extends BaseAdapter {
    Context context;
    ArrayList<Books> arraylistsachbanchay;

    public SachbanchayAdapter(Context context, ArrayList<Books> arraylistsachbanchay) {
        this.context = context;
        this.arraylistsachbanchay = arraylistsachbanchay;
    }

    @Override
    public int getCount() {
        return arraylistsachbanchay.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistsachbanchay.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {
        public TextView txttensachbanchay, txtgiasachbanchay, txtmotasachbanchay;
        public ImageView imgsachbanchay;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_sachbanchay, null);
            viewHolder.txttensachbanchay = (TextView) view.findViewById(R.id.textviewsachbanchay);
            viewHolder.txtgiasachbanchay = (TextView) view.findViewById(R.id.textviewgiasachbanchay);
            viewHolder.txtmotasachbanchay = (TextView) view.findViewById(R.id.textviewmotasachbanchay);
            viewHolder.imgsachbanchay = (ImageView) view.findViewById(R.id.imageviewsachbanchay);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Books books = (Books) getItem(i);
        viewHolder.txttensachbanchay.setText(books.getTensach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiasachbanchay.setText("Giá: " + decimalFormat.format(books.getGia()) + ".000 VNĐ");
        viewHolder.txtmotasachbanchay.setMaxLines(2);
        viewHolder.txtmotasachbanchay.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasachbanchay.setText(books.getGioithieusp());
        Picasso.with(context).load(books.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsachbanchay);

        return view;
    }
}