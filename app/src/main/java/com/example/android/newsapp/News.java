package com.example.android.newsapp;

/**
 * Created by High Spec on 10/28/2017.
 */

public class News {
    private String sectionName;
    private String webTitle;
    private String publicationDate;
    private String webUrl;
    private String authorName;

    public News(String sectionName, String publicationDate, String webTitle, String authorName, String webUrl) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.publicationDate = publicationDate;
        this.authorName = authorName;
        this.webUrl = webUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getAuthorName() {
        return authorName;
    }
}
