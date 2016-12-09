package com.powerthindownloadmanager.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.power.thin.downloadmanager.DownloadRequest;
import com.power.thin.downloadmanager.DownloadStatusListener;
import com.power.thin.downloadmanager.ThinDownloadManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static ThinDownloadManager mThinDownloadManager = new ThinDownloadManager();

    private ProgressBar mProgressBar1;
    private ProgressBar mProgressBar2;
    private ProgressBar mProgressBar3;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

    private Button mStopButton1;
    private Button mStopButton2;
    private Button mStopButton3;


    private List<String> mDownloadUrls = new ArrayList<String>(Arrays.asList(
            "http://dldir1.qq.com/weixin/android/weixin6331android940.apk",
            "https://t.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk",
            "http://app.mail.qq.com/cgi-bin/mailapp?latest=y&from=2&downloadclick="
    ));

    private List<String> mDownloadPaths = new ArrayList<>(Arrays.asList(
            "WeChat",
            "AliPay",
            "QQMail"
    ));

    private DownloadRequest mDownloadRequest1;
    private DownloadRequest mDownloadRequest2;
    private DownloadRequest mDownloadRequest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setViewStatus();
    }

    private void initView() {
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        mProgressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton1.setText(R.string.start);
        mButton2.setText(R.string.start);
        mButton3.setText(R.string.start);

        mStopButton1 = (Button) findViewById(R.id.button11);
        mStopButton2 = (Button) findViewById(R.id.button22);
        mStopButton3 = (Button) findViewById(R.id.button33);
    }

    private void setViewStatus() {
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) ((TextView) v).getText();
                if (getString(R.string.start).equals(text)) {
                    String path1 = getApplicationContext().getCacheDir() + File.separator + mDownloadPaths.get(0);
                    mDownloadRequest1 = DownloadUtils.startDownload(getApplicationContext(), mDownloadUrls.get(0), path1, mProgressBar1);
                    mThinDownloadManager.add(mDownloadRequest1);
                }
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = (String) ((TextView) v).getText();
                if (getString(R.string.start).equals(text)) {
                    String path2 = getApplicationContext().getCacheDir() + File.separator + mDownloadPaths.get(1);
                    mDownloadRequest2 = DownloadUtils.startDownload(getApplicationContext(), mDownloadUrls.get(1), path2, mProgressBar2);
                    mThinDownloadManager.add(mDownloadRequest2);
                }
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = (String) ((TextView) v).getText();
                if (getString(R.string.start).equals(text)) {
                    String path3 = getApplicationContext().getCacheDir() + File.separator + mDownloadPaths.get(2);
                    mDownloadRequest3 = DownloadUtils.startDownload(getApplicationContext(), mDownloadUrls.get(2), path3, mProgressBar3);
                    mThinDownloadManager.add(mDownloadRequest3);
                }
            }
        });

        mStopButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadRequest1 != null) {
                    mDownloadRequest1.cancel();
                }
            }
        });

        mStopButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadRequest2 != null) {
                    mDownloadRequest2.cancel();
                }
            }
        });

        mStopButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadRequest3 != null) {
                    mDownloadRequest3.cancel();
                }
            }
        });
    }
}
