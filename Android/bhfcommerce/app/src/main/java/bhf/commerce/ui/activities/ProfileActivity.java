package bhf.commerce.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import bhf.commerce.R;
import bhf.commerce.ui.fragments.MeetResearcherFragment;
import bhf.commerce.ui.fragments.MoneyRaisedFragment;
import bhf.commerce.ui.fragments.MyIdFragment;
import bhf.commerce.ui.fragments.PreferencesFragment;
import bhf.commerce.ui.views.ProfileMenu;

public class ProfileActivity extends AppCompatActivity implements ProfileMenu.OnMenuClicked, View.OnClickListener{

    private Fragment fragments[] = new Fragment[4];
    private View giftAid;
    private TextView giftAidStatus;
    boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        giftAidStatus = (TextView) findViewById(R.id.gift_aid_status);
        ProfileMenu menu = (ProfileMenu) findViewById(R.id.profile_menu);
        menu.setOnMenuClicked(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        giftAid = findViewById(R.id.gift_aid);
        giftAid.setOnClickListener(this);

        fragments[0] = new MyIdFragment();
        fragments[1] = new MoneyRaisedFragment();
        fragments[2] = new PreferencesFragment();
        fragments[3] = new MeetResearcherFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(click) {
            giftAidStatus.setText("Gift Aid: Yes");
            giftAid.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuClick(int menu) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, fragments[menu]);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, GiftAidSignupActivity.class);
        startActivity(intent);
        click = true;
    }
}
