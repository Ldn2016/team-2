package bhf.commerce.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
    private OnFragmentInteractionListener mListener;

    public ItemListDonatedFragment() {
        // Required empty public constructor
    }

    public static ItemListDonatedFragment newInstance(String param1, String param2) {
        ItemListDonatedFragment fragment = new ItemListDonatedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public String getTitle(){
        return "Donated";
    }

    @Override
    public void onItemFetched(Item[] items) {
        setData(items);
    }

    public void setData(Item items[]){
        for(Item item : items){
            if(!item.getStatus().equals("queue")){
                donated.add(item);
            }
        }
    }
}
