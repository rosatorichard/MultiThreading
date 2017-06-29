package com.batchmates.android.multithreading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.*;

public class MainActivity extends AppCompatActivity {

    private TextView text, text2, text3;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text= (TextView)findViewById(R.id.tvUpdate);
        text2=(TextView)findViewById(R.id.tvUpdate2);
        text3=(TextView)findViewById(R.id.tvUpdate3);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);



    }

    public void testRunnable(View view) {

        Thread t=new Thread(text,text2);

        Log.d("Runnable", "testRunnable: "+"Runable");
        Log.d("rest", "testRunnable: ");
        java.lang.Thread j =new java.lang.Thread(t);


        j.start();
    }

    public void StartThread(View view) {

        TestThread tester=new TestThread(text,text2);


        tester.start();
    }

    public void StartAsync(View view) {


        TestAsyncTask asyncTask=new TestAsyncTask(text3,progressBar);

        asyncTask.execute();




    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HelloEvent event)
    {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }



}
