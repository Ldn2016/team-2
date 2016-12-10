package bhf.commerce.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import bhf.commerce.R;

/**
 * Created by whdinata on 12/10/16.
 */
public class ProfileMenu extends LinearLayout implements View.OnClickListener{
    private View myId;
    private View moneyRaised;
    private View preferences;
    private View meetResearcher;
    private OnMenuClicked listener;

    public ProfileMenu(Context context) {
        super(context);
        initView();
    }

    public ProfileMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ProfileMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){

        View view = inflate(getContext(), R.layout.layout_profile_menu, this);
        myId = view.findViewById(R.id.id);
        moneyRaised = view.findViewById(R.id.raised);
        preferences = view.findViewById(R.id.preferences);
        meetResearcher = view.findViewById(R.id.meet);

        myId.setOnClickListener(this);
        moneyRaised.setOnClickListener(this);
        preferences.setOnClickListener(this);
        meetResearcher.setOnClickListener(this);
        reset();
        myId.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    private void reset(){
        myId.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        moneyRaised.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        preferences.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        meetResearcher.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onClick(View view) {
        reset();
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        listener.onMenuClick(Integer.parseInt((String)view.getTag()));
    }

    public void setOnMenuClicked(OnMenuClicked listener){
        this.listener = listener;
    }

    public interface OnMenuClicked{
        void onMenuClick(int menu);
    }
}
