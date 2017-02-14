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

public class CustomAdapter_List extends BaseAdapter {

    Context context;
    ArrayList<Post2> posts2;
    LayoutInflater inflater;
//    int layoutResourceId;
    
    public CustomAdapter_List(Context context, ArrayList<Post2> posts2){

        this.context = context;
        this.posts2 = posts2;
//        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return posts2.size();
    }

    @Override
    public Object getItem(int position) {
        return posts2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        TextView textView1,textView2,textView3;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_listview,parent,false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.list_id);
            holder.textView2 = (TextView) convertView.findViewById(R.id.list_cat_id);
            holder.textView3=(TextView)convertView.findViewById(R.id.list_quotes);

            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
//        holder.textView1.setText(posts2.get(position).getId());
//        holder.textView2.setText(posts2.get(position).getCat_id());

        holder.textView3.setText(posts2.get(position).getQuotes());

        holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MainActivity3.class);
                intent.putExtra("position",posts2.get(position).getQuotes());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
