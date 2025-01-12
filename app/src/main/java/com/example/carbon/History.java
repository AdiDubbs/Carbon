package com.example.carbon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class History extends ArrayAdapter<UserFootprint>
{
    private final Activity context;
    private final List<UserFootprint> historyList;

    public History(Activity context,List<UserFootprint>historyList)
    {
        super(context, R.layout.activity_footprint_history,historyList);
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.activity_footprint_history,null,true);
        TextView viewFootprint = viewItem.findViewById(R.id.history_footprint);
        TextView viewTime = viewItem.findViewById(R.id.history_time);
        UserFootprint user = historyList.get(position);
        viewFootprint.setText(user.getFootprint());
        viewTime.setText(user.getUserDate());
        return viewItem;
    }
}