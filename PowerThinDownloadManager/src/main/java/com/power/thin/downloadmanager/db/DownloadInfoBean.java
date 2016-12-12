package com.power.thin.downloadmanager.db;


public class DownloadInfoBean {
    private int mRequestId;
    private String mDownloadUrl;
    private String mDestinationPath;
    private String mTotalSize;
    private String mDownloadSize;

    public int getRequestId() {
        return mRequestId;
    }

    public void setRequestId(int requestId) {
        this.mRequestId = requestId;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.mDownloadUrl = downloadUrl;
    }

    public String getDestinationPath() {
        return mDestinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.mDestinationPath = destinationPath;
    }

    public String getTotalSize() {
        return mTotalSize;
    }

    public void setTotalSize(String totalSize) {
        this.mTotalSize = totalSize;
    }

    public String getDownloadSize() {
        return mDownloadSize;
    }

    public void setDownloadSize(String downloadSize) {
        this.mDownloadSize = downloadSize;
    }

    @Override
    public String toString() {
        return "requestId=" + mRequestId + "; "
                + "downloadUrl=" + mDownloadUrl + "; "
                + "destinationPath=" + mDestinationPath + "; "
                + "totalSize=" + mTotalSize + ";"
                + "downloadSize=" + mDownloadSize + ". ";
    }
}
