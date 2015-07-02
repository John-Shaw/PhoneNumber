package com.example.PhoneNumber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.json.JSONException;

import java.io.IOException;

public class MyActivity extends Activity {
    private LinearLayout phoneInfo;
    private EditText input;
    private Button search;
    private ImageView operatorImage;
    private TextView area;
    private TextView area_code;
    private TextView post_code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        phoneInfo = (LinearLayout) findViewById(R.id.linearLayout_phoneInfo);
        input = (EditText) findViewById(R.id.editText_phone_input);
        search = (Button) findViewById(R.id.button_search);
        operatorImage = (ImageView) findViewById(R.id.image_main_operator);
        area = (TextView) findViewById(R.id.textView_area);
        area_code = (TextView) findViewById(R.id.textView_areaCode);
        post_code = (TextView) findViewById(R.id.textView_postCode);

        phoneInfo.setVisibility(View.INVISIBLE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phoneNumber = input.getText().toString();
                        try {
                            PhoneInfo phoneInfo = new PhoneInfoFetcher().fetchPhoneInfo(phoneNumber);
                            if (phoneInfo.isQuerySuccess()) {
                                updatePhoneInfo(phoneInfo);
                            } else {
                                dialogShow();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.operator) {
            Intent intent = new Intent(MyActivity.this, SelectorActivity.class);
            startActivity(intent);
        }
        if (id==R.id.about) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.about)
                    .setMessage(R.string.about_message)
                    .setPositiveButton(R.string.ok, null)
                    .create()
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updatePhoneInfo(final PhoneInfo phoneInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyActivity.this.phoneInfo.setVisibility(View.VISIBLE);

                operatorImage.setImageResource(phoneInfo.getOperatorImage());
                area.setText("地区：" + phoneInfo.getProvince() + " " + phoneInfo.getCity());
                area_code.setText("区号：" + phoneInfo.getAreaCode());
                post_code.setText("邮编：" + phoneInfo.getPostCode());

            }
        });
    }

    private void dialogShow() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MyActivity.this);
                dialog.setTitle("出错");
                dialog.setMessage("未查到指定号码！");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                dialog.show();
            }
        });
    }



}
