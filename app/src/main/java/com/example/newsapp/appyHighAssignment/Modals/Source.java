/*
 * Created BY MEHRAJ UD DIN BHAT on 5/6/21 10:34 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:34 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.Modals;

public class Source {
    private  int Id;
    private String name;

    public Source() {
    }

    public Source(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
