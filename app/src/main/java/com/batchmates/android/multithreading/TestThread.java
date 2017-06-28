package com.batchmates.android.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.lang.*;

/**
 * Created by Android on 6/28/2017.
 */

public class TestThread extends java.lang.Thread{


    Handler handle= new Handler(Looper.getMainLooper());
    int i;

    TextView text;

    TextView slowText;

    public TestThread(TextView text,TextView text2) {
        this.text = text;
        this.slowText=text2;
    }

    @Override
    public void run() {
        super.run();
        for ( i = 0; i < 10; i++) {


                try {
                    java.lang.Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handle.post(new Runnable() {
                    @Override
                    public void run() {

                        text.setText(String.valueOf(i));


                    }
                });
                System.out.println("This thread is"+ java.lang.Thread.currentThread());

            handle.postDelayed(new Runnable() {
                @Override
                public void run() {

                    slowText.setText(String.valueOf(i));
                }
            },2000);
        }
    }
}
