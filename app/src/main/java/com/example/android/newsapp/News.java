package com.example.android.newsapp;

/**
 * Created by kempm on 1/8/2017.
 */

public class News {

    // Title of article
    String webTitle;

    // Date it was published
    String webPublicationDate;

    // Url to see full article
    String webUrl;

    String sectionName;

    // Constructor
    public News(String webTitle, String webPublicationDate, String webUrl, String sectionName) {
        this.webTitle = webTitle;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
        this.sectionName = sectionName;
    }

    // Getters
    public String getWebTitle() {
        return webTitle;
    }
    public String getWebPublicationDate() {
        return webPublicationDate;
    }
    public String getWebUrl() {
        return webUrl;
    }
    public String getSectionName() {
        return sectionName;
    }
}
