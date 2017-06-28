package com.batchmates.android.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

/**
 * Created by Android on 6/28/2017.
 */

public class Thread implements Runnable{

    TextView textView, slowtext;
    int i=0;
    private Handler handler=new Handler(Looper.getMainLooper());


    public Thread(TextView textView, TextView text) {
        this.textView = textView;
        this.slowtext=text;
    }

    @Override
    public void run()
    {


        for ( i = 0; i <10 ; i++) {


            try {
                java.lang.Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(i));
                }
            });
            System.out.println("This thread is"+ java.lang.Thread.currentThread());


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    slowtext.setText(String.valueOf(i));

                }
            },3000);

        }
    }


    public void runnable(int i)
    {

    }

}
