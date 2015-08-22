package com.example.baoadr01.myfriends.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by BaoADR01 on 8/22/2015.
 */
public class ShowListFavoriteAdapter extends BaseAdapter {
    List<MyContact> list;
    Context context;
    public ShowListFavoriteAdapter(Context context,List<MyContact> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_mycontact, parent, false);
        MyContact myContact = (MyContact) getItem(position);
        ImageView imageButton = (ImageView) rowView.findViewById(R.id.image_view_avatar);
        TextView textView = (TextView) rowView.findViewById(R.id.textview_view_name);

        ByteArrayInputStream imageStream = new ByteArrayInputStream(myContact.getIMAGE());
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        imageButton.setImageBitmap(theImage);
        textView.setText(myContact.getNAME());
        return rowView;
    }
}
