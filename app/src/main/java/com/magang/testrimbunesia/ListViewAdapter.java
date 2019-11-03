package com.magang.testrimbunesia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<PlayerItem> {

    private List<PlayerItem> playerItemList;

    private Context context;

    public ListViewAdapter(List<PlayerItem> playerItemList, Context context) {
        super(context, R.layout.list_item, playerItemList);
        this.playerItemList = playerItemList;
        this.context = context;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewClub = listViewItem.findViewById(R.id.textViewClub);


        PlayerItem playerItem = playerItemList.get(position);

        textViewName.setText(playerItem.getName());
        textViewClub.setText(playerItem.getClub());


        return listViewItem;
    }
}