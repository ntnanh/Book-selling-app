    package com.example.appbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbook.R;
import com.example.appbook.model.Theloai;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
public class TheloaiAdapter extends BaseAdapter {
    ArrayList<Theloai> arraylisttheloai;
    Context context;

    public TheloaiAdapter(ArrayList<Theloai> arraylisttheloai, Context context) {
        this.arraylisttheloai = arraylisttheloai;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylisttheloai.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylisttheloai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttentheloai;
        ImageView imgtheloai;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.txttentheloai = (TextView) view.findViewById(R.id.textviewtheloai);
//            viewHolder.txttentheloai = (TextView) view.findViewById(R.id.textviewtheloai;
            viewHolder.imgtheloai = (ImageView) view.findViewById(R.id.imageviewtheloai);
            view.setTag(viewHolder);


        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Theloai theloai = (Theloai) getItem(i);
        viewHolder.txttentheloai.setText(theloai.getTentheloai());
//        Picasso.with(context).load(theloai.getHinhanhtheloai())
//                .placeholder(R.drawable.noimage)
//                .error(R.drawable.error)
//                .into(viewHolder.imgtheloai);
        Picasso.with(context).load(new File(theloai.getHinhanhtheloai()))
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgtheloai);
        return view;
    }
}
