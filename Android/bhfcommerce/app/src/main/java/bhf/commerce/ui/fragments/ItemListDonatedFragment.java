package bhf.commerce.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import bhf.commerce.R;
import bhf.commerce.callbacks.OnListItemFetched;
import bhf.commerce.connections.API;
import bhf.commerce.models.Item;
import bhf.commerce.ui.adapters.ItemAdapter;


public class ItemListDonatedFragment extends ItemListFragment implements OnListItemFetched {
    List<Item> donated = new ArrayList<>();
    protected ItemAdapter adapter;

    public ItemListDonatedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_donated, null);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        adapter = new ItemAdapter(getContext());
        gridView.setAdapter(adapter);

        API.init(getActivity());
        API.getListItems(this);

        return view;
    }

    public String getTitle(){
        return "Donated";
    }

    @Override
    public void onItemFetched(Item[] items) {
        setData(items);
    }

    public void setData(Item items[]){
        if(items.length > 0) {
            for (Item item : items) {
                if (!item.getStatus().equals("queue")) {
                    donated.add(item);
                }
            }
        }
    }
}
