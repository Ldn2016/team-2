package bhf.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bhf.commerce.ui.activities.AddItemActivity;
import bhf.commerce.ui.activities.ItemListActivity;
import bhf.commerce.ui.activities.ProfileActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.profile).setOnClickListener(this);
        findViewById(R.id.donation).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.donation:
                intent = new Intent(this, AddItemActivity.class);
                startActivity(intent);
                break;
        }
    }
}

