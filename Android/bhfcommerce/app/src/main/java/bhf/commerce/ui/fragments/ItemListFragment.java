package bhf.commerce.ui.fragments;

import android.support.v4.app.Fragment;

import java.util.List;

import bhf.commerce.models.Item;

public abstract class ItemListFragment extends Fragment {
    private List<Item> items;

    public ItemListFragment() {

    }

    public abstract String getTitle();
}
