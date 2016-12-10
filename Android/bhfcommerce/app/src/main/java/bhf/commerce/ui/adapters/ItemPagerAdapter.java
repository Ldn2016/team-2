package bhf.commerce.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import bhf.commerce.models.Item;
import bhf.commerce.ui.fragments.ItemListDonatedFragment;
import bhf.commerce.ui.fragments.ItemListFragment;
import bhf.commerce.ui.fragments.ItemListNotDonatedFragment;

public class ItemPagerAdapter extends FragmentPagerAdapter {

    ItemListFragment fragments[] = new ItemListFragment[2];

    public ItemPagerAdapter(FragmentManager fm){
        super(fm);
        fragments[0] = new ItemListDonatedFragment();
        fragments[1] = new ItemListNotDonatedFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new ItemListDonatedFragment();

        return new ItemListNotDonatedFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getTitle();
    }
}
