package bhf.commerce.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bhf.commerce.R;
import bhf.commerce.utils.Holder;

public class ResearchDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private View button;
    private int number;

    public static Intent createIntent(Context context, int researchNumber){
        Intent intent = new Intent(context, ResearchDetailActivity.class);
        intent.putExtra("number", researchNumber);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.image);
        TextView title = (TextView) findViewById(R.id.title);
        TextView body = (TextView) findViewById(R.id.body);

        button = findViewById(R.id.subscribe);
        button.setOnClickListener(this);


        number = getIntent().getIntExtra("number", 0);

        if(Holder.list.contains(number)){
            button.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
        }

        switch (number){
            case 1:
                image.setImageResource(R.drawable.coronary);
                title.setText("Coronary Heart Disease");
                body.setText(Html.fromHtml(getString(R.string.coronary)));
                break;
            case 2:
                image.setImageResource(R.drawable.inherited);
                title.setText("Inherited Heart conditions");
                body.setText(Html.fromHtml(getString(R.string.inherited)));
                break;
            case 3:
                image.setImageResource(R.drawable.regenerative);
                title.setText("Regenerative Medicine");
                body.setText(Html.fromHtml(getString(R.string.regenerative)));
                break;
            case 4:
                image.setImageResource(R.drawable.atrial);
                title.setText("Atrial Fibrillation");
                body.setText(Html.fromHtml(getString(R.string.atrial)));
                break;
            case 5:
                image.setImageResource(R.drawable.cardiomyopathy);
                title.setText("Cardiomyopathy");
                body.setText(Html.fromHtml(getString(R.string.cardiomyopathy)));
                break;
            case 6:
                image.setImageResource(R.drawable.septic);
                title.setText("Septic Shock");
                body.setText(Html.fromHtml(getString(R.string.septic)));
                break;

        }
    }

    @Override
    public void onClick(View view) {
        if(Holder.list.contains(number)){
            Holder.remove(number);
            button.setBackgroundColor(getResources().getColor(R.color.button_unsubscribe));
        } else {
            Holder.list.add(number);
            button.setBackgroundColor(getResources().getColor(R.color.button_subscribe));
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
}
