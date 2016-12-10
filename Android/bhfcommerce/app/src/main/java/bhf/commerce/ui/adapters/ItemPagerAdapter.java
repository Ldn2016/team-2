package bhf.commerce.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import bhf.commerce.ui.fragments.ItemListDonatedFragment;
import bhf.commerce.ui.fragments.ItemListFragment;
import bhf.commerce.ui.fragments.ItemListNotDonatedFragment;

/**
 * Created by whdinata on 12/9/16.
 */
public class ItemPagerAdapter extends FragmentStatePagerAdapter {
    ItemListFragment fragments[] = new ItemListFragment[2];

    public ItemPagerAdapter(FragmentManager fm){
        super(fm);
        fragments[0] = new ItemListDonatedFragment();
        fragments[1] = new ItemListNotDonatedFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getTitle();
    }
}