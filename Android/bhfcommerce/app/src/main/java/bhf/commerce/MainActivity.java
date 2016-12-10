package bhf.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bhf.commerce.ui.activities.AddItemActivity;
import bhf.commerce.ui.activities.ItemListActivity;
import bhf.commerce.ui.activities.ProfileActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
