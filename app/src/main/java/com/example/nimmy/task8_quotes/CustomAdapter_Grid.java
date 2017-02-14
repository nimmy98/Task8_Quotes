package com.example.nimmy.task8_quotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nimmy on 13-02-2017.
 */

public class CustomAdapter_Grid extends BaseAdapter {


    Context context;
    ArrayList<Post> posts;
    LayoutInflater inflater;
//    int layoutResourceId;

    public CustomAdapter_Grid(Context context,ArrayList<Post> posts){
        this.context = context;
        this.posts = posts;
//        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return  posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {

        TextView id,name;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(layoutResourceId,parent,false);
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_gridview,parent,false);
            holder.id = (TextView) convertView.findViewById(R.id.main_TV1);
            holder.name = (TextView) convertView.findViewById(R.id.main_TV2);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

//        Post post = posts.get(position);
//        holder.id.setText(post.getid());
//        holder.name.setText(post.getname());
        holder.name.setText(posts.get(position).getname());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context,MainActivity2.class);
                intent.putExtra("position",posts.get(position).getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
