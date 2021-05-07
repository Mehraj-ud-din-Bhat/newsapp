/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 10:42 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.MainScreen;

import com.example.newsapp.appyHighAssignment.Application.IbaseApp;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;

import java.util.List;

public interface ImainScreenView  extends IbaseApp {
         void  getTopHeadlines(String countryCode);
         void  onTopHeadlinesRecieved(List<NewsArticle> newsArticleList);


}
