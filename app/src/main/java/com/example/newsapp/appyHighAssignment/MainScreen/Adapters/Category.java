/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 10:51 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 10:51 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.MainScreen.Adapters;

import java.util.ArrayList;

public class Category {
        private  String name;
        private  int imageId;

        public Category(String name, int imageId) {
                this.name = name;
                this.imageId = imageId;
        }


        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getImageId() {
                return imageId;
        }

        public void setImageId(int imageId) {
                this.imageId = imageId;
        }
}
