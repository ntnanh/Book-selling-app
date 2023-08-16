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
import com.example.appbook.model.Book;
import com.example.appbook.model.Books;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SachmoiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Books> arraylistsachmoi;

    public SachmoiAdapter(Context context, ArrayList<Books> arraylistsachmoi) {
        this.context = context;
        this.arraylistsachmoi = arraylistsachmoi;
    }

    @Override
    public int getCount() {
        return arraylistsachmoi.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistsachmoi.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }
    public class ViewHolder{
        public TextView txttensachmoi, txtgiasachmoi, txtmotasachmoi;
        public ImageView imgsachmoi;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_sachmoi,null);
            viewHolder.txttensachmoi = (TextView) view.findViewById(R.id.textviewsachmoi);
            viewHolder.txtgiasachmoi = (TextView) view.findViewById(R.id.textviewgiasachmoi);
            viewHolder.txtmotasachmoi = (TextView) view.findViewById(R.id.textviewmotasachmoi);
            viewHolder.imgsachmoi = (ImageView) view.findViewById(R.id.imageviewsachmoi);
            view.setTag(viewHolder);

        }else {
            viewHolder = (SachmoiAdapter.ViewHolder) view.getTag();

        }
        Books books =(Books) getItem(i);
        viewHolder.txttensachmoi.setText(books.getTensach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiasachmoi.setText("Giá: " + decimalFormat.format(books.getGia())+ ".000 VNĐ");
        viewHolder.txtmotasachmoi.setMaxLines(2);
        viewHolder.txtmotasachmoi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasachmoi.setText(books.getGioithieusp());
        Picasso.with(context).load(books.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsachmoi);

        return view;
    }
}
