/*
 * Created BY MEHRAJ UD DIN BHAT on 5/6/21 10:33 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:31 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.Modals;

import java.util.Date;

public class NewsArticle {
    private  Source source;
    private String author;
    private String title;
    private String description;
    private String  url;
    private String urlToImage;
    private Date publishedAt;
    private String content;

    public NewsArticle() {
    }

    public NewsArticle(Source source, String author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }


    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
