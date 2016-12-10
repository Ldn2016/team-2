package bhf.commerce.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bhf.commerce.R;
import bhf.commerce.ui.activities.ResearchDetailActivity;
import bhf.commerce.utils.Holder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PreferencesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PreferencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener{

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;

    private OnFragmentInteractionListener mListener;

    public PreferencesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoneyRaisedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreferencesFragment newInstance(String param1, String param2) {
        PreferencesFragment fragment = new PreferencesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Holder.list.contains(1)){
            tv1.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        } else{
            tv1.setBackgroundColor(Color.WHITE);
        }

        if(Holder.list.contains(2)){
            tv2.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        } else{
            tv2.setBackgroundColor(Color.WHITE);
        }

        if(Holder.list.contains(3)){
            tv3.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        } else{
            tv3.setBackgroundColor(Color.WHITE);
        }

        if(Holder.list.contains(4)){
            tv4.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        } else{
            tv4.setBackgroundColor(Color.WHITE);
        }

        if(Holder.list.contains(5)){
            tv5.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        } else{
            tv5.setBackgroundColor(Color.WHITE);
        }

        if(Holder.list.contains(6)){
            tv6.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        } else{
            tv6.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, null);
        tv1 = (TextView) view.findViewById(R.id.text1);
        tv2 = (TextView) view.findViewById(R.id.text2);
        tv3 = (TextView) view.findViewById(R.id.text3);
        tv4 = (TextView) view.findViewById(R.id.text4);
        tv5 = (TextView) view.findViewById(R.id.text5);
        tv6 = (TextView) view.findViewById(R.id.text6);

        tv1.setText(Html.fromHtml(getString(R.string.coronary)));
        tv2.setText(Html.fromHtml(getString(R.string.inherited)));
        tv3.setText(Html.fromHtml(getString(R.string.regenerative)));
        tv4.setText(Html.fromHtml(getString(R.string.atrial)));
        tv5.setText(Html.fromHtml(getString(R.string.cardiomyopathy)));
        tv6.setText(Html.fromHtml(getString(R.string.septic)));

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        int number = 0;
        switch (view.getId()){
            case R.id.text1:
                number = 1;
                break;
            case R.id.text2:
                number = 2;
                break;
            case R.id.text3:
                number = 3;
                break;
            case R.id.text4:
                number = 4;
                break;
            case R.id.text5:
                number = 5;
                break;
            case R.id.text6:
                number = 6;
                break;
        }

        Intent intent = ResearchDetailActivity.createIntent(getActivity(), number);
        startActivity(intent);
    }
}
