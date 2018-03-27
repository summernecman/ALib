package com.android.lib.util.thread;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;

import com.android.lib.base.fragment.BaseFrg;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.FragAdapter;
import com.android.lib.base.interf.OnLoadingInterf;

/**
 * Created by ${viwmox} on 2017-02-20.
 */

public class ThreadUtil {

    Integer max = 0;
    Handler handler = new Handler();
    private boolean stop = false;

    Thread thread;
    ;

    public void run(final long time, final OnLoadingInterf listener) {
        stop = false;
        max = 0;
        new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                while (!stop) {
                    max++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStarLoading(max);
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listener.onStopLoading(max);
                stop = true;
            }
        }.execute();
    }

    public void run2(final long time, final OnLoadingInterf listener) {
        stop = false;
        max = 0;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    max++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStarLoading(max);
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                listener.onStopLoading(max);
            }
        });
        thread.run();
    }


    public void run(BaseUIFrag baseUIFrag, final View view, final int maxx, final long time, final OnLoadingInterf listener) {
        stop = false;
        max = 0;
        view.setEnabled(false);
        new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                while (!stop && max<maxx) {
                    max++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStarLoading(max);
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listener.onStopLoading(max);
                stop = true;
                view.setEnabled(true);
            }
        }.execute();

        baseUIFrag.addFragListener(new FragAdapter(){
            @Override
            public void onDestroy() {
                super.onDestroy();
                view.setEnabled(true);
                stop = true;
                max = 0;
            }
        });
    }

    public void stop() {
        stop = true;
    }


}
