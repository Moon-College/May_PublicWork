package com.lin.myobserver.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.lin.myobserver.app.Observer.RealClickObserver;
import com.lin.myobserver.app.observeable.RealClickObserveable;


public class MainActivity extends ActionBarActivity {

    private RealClickObserveable realClickObserveable;
    private RealClickObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View bt = this.findViewById(R.id.bt);

        realClickObserveable = new RealClickObserveable();
        observer = new RealClickObserver();
        realClickObserveable.registeObserver(observer);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realClickObserveable.notifyAllObserver();


            }
        });
    }


}
