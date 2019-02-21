package com.pad.sjali.id.sjalipad.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pad.sjali.id.sjalipad.Model.Book;
import com.pad.sjali.id.sjalipad.R;

import java.util.ArrayList;


public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder> {


    private ArrayList<Book> dataList;

    public TimelineAdapter(ArrayList<Book> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new TimelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.txtName.setText(dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TimelineViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtName;

        public TimelineViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_book_tile);
            txtName = itemView.findViewById(R.id.txt_book_user_name);
        }
    }
}