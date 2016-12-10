package bhf.commerce.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bhf.commerce.connections.API;
import bhf.commerce.models.Item;

/**
 * Created by whdinata on 12/10/16.
 */
public class ItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<Item> items;

    public ItemAdapter(Context c) {
        mContext = c;
        items = new ArrayList<>();
    }

    public int getCount() {
        System.out.println("Get Count: " + items.size());
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public void setData(List<Item> items){
        this.items = items;

        System.out.println("Items Data: " + items.size());
        notifyDataSetChanged();
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        System.out.println("items " + items.get(position).getImageUrl());
        Glide.with(mContext).load(API.HOST + items.get(position).getImageUrl()).into(imageView);
        return imageView;
    }
}