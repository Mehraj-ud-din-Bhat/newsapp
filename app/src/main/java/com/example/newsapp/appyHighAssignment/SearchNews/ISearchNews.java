/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 10:13 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 10:13 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.SearchNews;

import com.example.newsapp.appyHighAssignment.Application.IbaseApp;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;

import java.util.List;

public interface ISearchNews extends IbaseApp {
       void onSearchResult(List<NewsArticle> list);
}
