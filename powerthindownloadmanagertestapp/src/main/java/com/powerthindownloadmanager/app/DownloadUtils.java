package com.powerthindownloadmanager.app;

import android.content.Context;
import android.net.Uri;
import android.widget.ProgressBar;

import com.power.thin.downloadmanager.DefaultRetryPolicy;
import com.power.thin.downloadmanager.DownloadRequest;
import com.power.thin.downloadmanager.DownloadStatusListener;

public class DownloadUtils {

    public static DownloadRequest startDownload(Context context, String url, String path, final ProgressBar progressBar) {
        Uri downloadUri = Uri.parse(url);
        Uri destinationUri = Uri.parse(path);
        DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                .setRetryPolicy(new DefaultRetryPolicy())
                .setDestinationURI(destinationUri)
                .setPriority(DownloadRequest.Priority.HIGH)
                .setDownloadContext(context)
                .setStatusListener(new DownloadStatusListener() {
                    @Override
                    public void onDownloadComplete(DownloadRequest downloadRequest) {

                    }

                    @Override
                    public void onDownloadFailed(DownloadRequest downloadRequest, int errorCode, String errorMessage) {
                        progressBar.setProgress(0);
                    }

                    @Override
                    public void onProgress(DownloadRequest downloadRequest, long totalBytes, long downloadedBytes, int progress) {
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void onStart(DownloadRequest downloadRequest, long contentLength) {
                        progressBar.setMax(100);
                    }
                });
        return downloadRequest;
    }
}
