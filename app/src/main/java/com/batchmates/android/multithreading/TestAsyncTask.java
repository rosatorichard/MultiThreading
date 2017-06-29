package com.batchmates.android.multithreading;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.lang.*;
import java.lang.Thread;

/**
 * Created by Android on 6/28/2017.
 */

public class TestAsyncTask extends AsyncTask<String,Integer,String>{

    TextView textView;
    ProgressBar progressBar;

    int i;
    public TestAsyncTask(TextView textView, ProgressBar progressBar) {
        this.textView = textView;
        this.progressBar=progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);;
        textView.setText("Starting Async");
    }

    @Override
    protected String doInBackground(String... strings) {
        for ( i = 0; i <= 10; i++) {

            try {
                java.lang.Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
        }

        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in Background"+Thread.currentThread());
        return "Task Done";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        textView.setText("in ProgUpdate "+String.valueOf(i));
        EventBus.getDefault().post((new HelloEvent("Hello: "+values[0]*10)));
        System.out.printf(""+values[0]);
        progressBar.setProgress(values[0]*10);
        System.out.println(Thread.currentThread());
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressBar.setVisibility(View.GONE);
        progressBar.setProgress(0);
        textView.setText(s);
        System.out.println("in Post Exec"+Thread.currentThread());
    }
}

