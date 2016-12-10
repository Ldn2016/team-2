package bhf.commerce.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import bhf.commerce.R;
import bhf.commerce.callbacks.OnListItemFetched;
import bhf.commerce.connections.API;
import bhf.commerce.models.Item;
import bhf.commerce.ui.adapters.ItemAdapter;
import bhf.commerce.ui.adapters.ItemPagerAdapter;

public class ItemListActivity extends AppCompatActivity implements OnListItemFetched {
    private ItemPagerAdapter adapter;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        itemAdapter = new ItemAdapter(this);
        gridView.setAdapter(itemAdapter);

        API.init(this);
        API.getListItems(this);
    }


    @Override
    public void onItemFetched(Item[] items) {
        setData(items);
    }

    public void setData(Item items[]){
        List<Item> list = new ArrayList<>();

        for(Item item : items){
            list.add(item);
        }

        itemAdapter.setData(list);
    }
}
