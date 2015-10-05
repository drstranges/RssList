package com.test.rsslist.data.model;

/**
 * Created on 05.10.2015.
 */
public class ApiResponse {
    private boolean isSuccess = false;
    private String errorMessage;
    private Rss rss;

    public ApiResponse(boolean isSuccess, String errorMessage, Rss rss) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.rss = rss;
    }

    public static ApiResponse successResponse(Rss rss) {
        return new ApiResponse(true, null, rss);
    }

    public static ApiResponse errorResponse(String errorMessage) {
        return new ApiResponse(false, errorMessage, null);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Rss getRss() {
        return rss;
    }
}
