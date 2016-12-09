package com.power.thin.downloadmanager;

/**
 * A Listener for the Download Status. Implement this interface to listen to Download Events.
 */
public interface DownloadStatusListener {

    /**
     * This method is invoked when download is complete.
     *
     * @param downloadRequest the download request provided by the client
     */
    void onDownloadComplete(DownloadRequest downloadRequest);


    /**
     * This method is invoked when download has failed.
     *
     * @param downloadRequest the download request provided by the client
     * @param errorCode       the download error code
     * @param errorMessage    the error message
     */
    void onDownloadFailed(DownloadRequest downloadRequest, int errorCode, String errorMessage);

    /**
     * This method is invoked on a progress update.
     *
     * @param downloadRequest the download request provided by the client
     * @param totalBytes      the total bytes
     * @param downloadedBytes bytes downloaded till now
     * @param progress        the progress of download
     */
    void onProgress(DownloadRequest downloadRequest, long totalBytes, long downloadedBytes, int progress);

    /**
     * This method is invoked when start download file.
     *
     * @param downloadRequest the download request provided by the client
     * @param contentLength   the total length of the download file.
     */
    void onStart(DownloadRequest downloadRequest, long contentLength);
}
