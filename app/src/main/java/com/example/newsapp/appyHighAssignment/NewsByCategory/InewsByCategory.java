/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 11:21 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 11:21 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.NewsByCategory;

import com.example.newsapp.appyHighAssignment.Application.IbaseApp;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;

import java.util.List;

public interface InewsByCategory extends IbaseApp {
     void getNewsByCategory(String category);
     void onNewsRecieved(List<NewsArticle> list);

}
