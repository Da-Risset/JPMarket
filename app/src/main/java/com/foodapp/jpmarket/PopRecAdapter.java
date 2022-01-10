package com.foodapp.jpmarket;

import android.content.Context;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.Product;

//public class PopRecAdapter extends RecyclerView.Adapter<PopRecAdapter.MyViewHolder> {
//
//    String data1[], data2[];
//    int images[];
//    Context context;
//    public PopRecAdapter(Context ct,String s1[], String s2[], int img[]){
//        context = ct;
//        data1=s1;
//        data2=s2;
//        images=img;
//
//    }
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.my_row, parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.myText1.setText(data1[position]);
//        holder.myText2.setText(data2[position]);
//        holder.myImage.setImageResource(images[position]);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return data1.length;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView myText1, myText2 ;
//        ImageView myImage;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            myText1 = itemView.findViewById(R.id.judul);
//            myText2 = itemView.findViewById(R.id.deskripsi);
//            myImage = itemView.findViewById(R.id.myimageView);
//
//        }
//    }
//}


public class PopRecAdapter extends RecyclerView.Adapter<PopRecAdapter.MyViewHolder> {

    private List<Product> dataList;
    private Context context;

    public PopRecAdapter(Context context,List<Product> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle, txtDescription;
        private ImageView coverImage;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.judul);
            txtDescription = mView.findViewById(R.id.deskripsi);
            coverImage = mView.findViewById(R.id.myimageView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.txtDescription.setText(dataList.get(position).getDescription());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnail())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}