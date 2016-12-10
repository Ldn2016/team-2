package bhf.commerce.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import bhf.commerce.R;
import bhf.commerce.callbacks.OnListItemFetched;
import bhf.commerce.connections.API;
import bhf.commerce.models.Item;
import bhf.commerce.ui.adapters.ItemPagerAdapter;
import bhf.commerce.ui.fragments.ItemListDonatedFragment;

public class ItemListActivity extends AppCompatActivity implements ItemListDonatedFragment.OnFragmentInteractionListener{
    private ItemPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new ItemPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
