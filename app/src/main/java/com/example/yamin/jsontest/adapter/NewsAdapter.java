package com.example.yamin.jsontest.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yamin.jsontest.tool.MyApplication;
import com.example.yamin.jsontest.News;
import com.example.yamin.jsontest.R;
import com.example.yamin.jsontest.aty.WebActivity;

import java.util.List;



/**
 * Created by yamin on 2017/5/28.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<News> mNewsList;
    String url;


    static class ViewHolder extends RecyclerView.ViewHolder{

        View newsView;

        TextView title;

        TextView data;

        TextView author;

        ImageView textIma;

        View divider;

        public ViewHolder(View view){
            super(view);

            newsView = view;
            title = (TextView)view.findViewById(R.id.title);
            data = (TextView)view.findViewById(R.id.data);
            author = (TextView)view.findViewById(R.id.author);
            textIma = (ImageView)view.findViewById(R.id.image_View);
            divider = (View)view.findViewById(R.id.divider);
        }
    }

    public NewsAdapter(List<News> newList){
        mNewsList = newList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list,parent,false);

        final ViewHolder holder = new ViewHolder(view);

        holder.newsView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                if (mNewsList.size() != 0) {     //防止在刷新期间，点击item会出错。因为没得到数据，size为0，点击是index不为0，而出错
                    News news = mNewsList.get(position);
                    url = news.getUrl();
                    if (url != null) {
                        Intent intent = new Intent(view.getContext(), WebActivity.class);
                        intent.putExtra("url", url);
                        view.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(view.getContext(), "no internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        News news = mNewsList.get(position);

        Glide.with(MyApplication.getContext()).load(news.getImageUrl()).into(holder.textIma);

        holder.author.setText(news.getAuthor());

        holder.title.setText(news.getTitle());

        holder.data.setText(news.getData());

        if (position == mNewsList.size() - 1 ){      //若是到达 RecyclerView 最后一项，则把分割线隐藏
            holder.divider.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
