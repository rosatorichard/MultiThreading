package com.batchmates.android.multithreading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.*;

public class MainActivity extends AppCompatActivity {

    private TextView text, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text= (TextView)findViewById(R.id.tvUpdate);
        text2=(TextView)findViewById(R.id.tvUpdate2);




    }

    public void testRunnable(View view) {

        Thread t=new Thread(text,text2);

        java.lang.Thread j =new java.lang.Thread(t);


        j.start();
    }

    public void StartThread(View view) {

        TestThread tester=new TestThread(text,text2);

        tester.start();
    }

    public void StartAsync(View view) {




    }
}
