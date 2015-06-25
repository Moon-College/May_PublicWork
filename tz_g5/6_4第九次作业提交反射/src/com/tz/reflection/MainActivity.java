package com.tz.reflection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tz.reflection.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends Activity {

    private TextView tv_one,tv_two,tv_three,tv_four,tv_five;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            ReflectionUtils.initViews(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        TextView tv = (TextView) view;
        Toast.makeText(getApplication(), tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
