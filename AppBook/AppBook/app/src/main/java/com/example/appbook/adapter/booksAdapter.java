package com.example.appbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbook.R;
import com.example.appbook.acitivity.DetailsProductActivity;
import com.example.appbook.model.Books;
import com.example.appbook.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class booksAdapter extends RecyclerView.Adapter<booksAdapter.ItemHolder> {
    Context context;
    ArrayList<Books> arraylistbooks;

    public booksAdapter(Context context, ArrayList<Books> arraylistbooks) {
        this.context = context;
        this.arraylistbooks = arraylistbooks;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Books books = arraylistbooks.get(position);
        holder.txttenbooks.setText(books.getTensach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiabooks.setText("Giá: " + decimalFormat.format(books.getGia()) + ".000 VNĐ");
        Picasso.with(context).load(books.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imghinhbooks);


    }

    @Override
    public int getItemCount() {
        return arraylistbooks.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imghinhbooks;
        public TextView txttenbooks, txtgiabooks;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhbooks = (ImageView) itemView.findViewById(R.id.imageviewbooks);
            txtgiabooks = (TextView) itemView.findViewById(R.id.txtviewgiabooks);
            txttenbooks = (TextView) itemView.findViewById(R.id.txttenbooks);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    Intent intent = new Intent(context, DetailsProductActivity.class);
                    intent.putExtra("Thongtinbooks",arraylistbooks.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context, arraylistbooks.get(getPosition()).getTensach());
                    context.startActivity(intent);
                }
            });
        }
    }
}
