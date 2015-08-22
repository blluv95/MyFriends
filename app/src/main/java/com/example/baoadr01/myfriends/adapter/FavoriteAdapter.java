package com.example.baoadr01.myfriends.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baoadr01.myfriends.R;
import com.example.baoadr01.myfriends.Utils.MyContact;
import com.example.baoadr01.myfriends.db.DatabaseHandler;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BaoADR01 on 8/21/2015.
 */
public class FavoriteAdapter extends BaseAdapter {
    DatabaseHandler db;
    List<MyContact> list;
    Context context;
    Map<Integer, Boolean> map;

    public FavoriteAdapter(Context context, List<MyContact> list) {
        this.context = context;
        this.list = list;
        map = new HashMap<Integer, Boolean>();
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


    class ViewHolder {
        CheckBox cb_favorite;
        TextView tv_favorite;
        ImageView im_favorite;
        int position;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favorite_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cb_favorite = (CheckBox) convertView.findViewById(R.id.checkbox_choose);
            viewHolder.tv_favorite = (TextView) convertView.findViewById(R.id.textview_favorite_name);
            viewHolder.im_favorite = (ImageView) convertView.findViewById(R.id.image_favorite_avatar);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        final MyContact myContact = list.get(position);
        viewHolder.position = position;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(myContact.getIMAGE());
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        viewHolder.im_favorite.setImageBitmap(theImage);
        viewHolder.tv_favorite.setText(myContact.getNAME());
        viewHolder.cb_favorite.setOnCheckedChangeListener(null);
        viewHolder.cb_favorite.setChecked(false);
        Log.i("Item -----=========>", ""+position);
        if (map.containsKey(position)) {
            viewHolder.cb_favorite.setChecked(map.get(position));  // L?u Check

        }
        viewHolder.cb_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                map.put(position, isChecked);
                if(isChecked) {
                    Log.i("Item Click---=>", "" + position);
                    myContact.setID_CHECKBOX(list.get(position).getID());
                }else{
                    myContact.setID_CHECKBOX(0);
                }


            }
        });
        return convertView;
    }
}
