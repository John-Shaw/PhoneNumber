package com.example.PhoneNumber;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by John on 15/7/2.
 */
public class IntroActivity extends Activity {
    private int[] operatorImages ={R.drawable.cmcc,R.drawable.unicom,R.drawable.telecom};
    private String[] operators;
    private String[] operatorsIntro;
    private ImageView image;
    private TextView title;
    private TextView intro;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        int position=getIntent().getIntExtra("position", 0);

        operators =getResources().getStringArray(R.array.operators);
        operatorsIntro =getResources().getStringArray(R.array.operators_intro);
        image = (ImageView) findViewById(R.id.image_intro_operator);
        title = (TextView) findViewById(R.id.textView_operator_name);
        intro = (TextView) findViewById(R.id.textView_operator_intro);

        image.setImageResource(operatorImages[position]);
        title.setText(operators[position]);
        intro.setText(operatorsIntro[position]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
