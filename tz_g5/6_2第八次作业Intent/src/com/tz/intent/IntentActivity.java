package com.tz.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.tz.intent.bean.Person;

/**
 * Created by qinhan on 15/6/4.
 */
public class IntentActivity extends Activity{
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);

        editText = (EditText) findViewById(R.id.et);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Person p = new Person("张三", 10);
                Intent intent = new Intent();
                intent.putExtra("data", p);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
