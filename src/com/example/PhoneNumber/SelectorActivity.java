package com.example.PhoneNumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by John on 15/7/2.
 */
public class SelectorActivity extends Activity {
    private String[] operators;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        operators =getResources().getStringArray(R.array.operators);

        listView= (ListView) findViewById(R.id.listView_operators);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, operators));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectorActivity.this, IntroActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

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
