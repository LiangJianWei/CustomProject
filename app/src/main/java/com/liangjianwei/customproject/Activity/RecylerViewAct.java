package com.liangjianwei.customproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liangjianwei.customproject.R;

import java.util.ArrayList;
import java.util.List;

public class RecylerViewAct extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);

        RecyclerView recyleView = (RecyclerView) findViewById(R.id.recyleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyleView.setLayoutManager(layoutManager);

        recyleView.setItemAnimator(new DefaultItemAnimator());



        List<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            items.add(i + "items");

        }

        recyleView.setAdapter(new MyApdater(items));


    }


    public class MyApdater extends RecyclerView.Adapter<MyApdater.ViewHolder> {

        List<String> items;


        public MyApdater(List<String> items) {
            this.items = (items == null ? new ArrayList<String>() : items);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_layout, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(items.get(position));

        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        protected class ViewHolder extends RecyclerView.ViewHolder {
            protected final TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.item_text);
            }
        }
    }

}
