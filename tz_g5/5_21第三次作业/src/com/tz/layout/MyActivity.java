package com.tz.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_1:
                intent = new Intent(this, HomeWorkOneActivity.class);
                break;
            case R.id.tv_2:
                intent = new Intent(this, HomeWorkTwoActivity.class);
                break;
        }
        startActivity(intent);
    }
}
