/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:19 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//-------------------------------------------
//THIS CLASS CONTAINS SOME UTILITY FUNCTIONS
//--------------------------------------------
public class Utility {
    public  static class CustomDate {

        public final static String getTimeDetails(String time) throws ParseException {
            final TimeZone time_Zone=TimeZone.getTimeZone("GMT");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            format.setTimeZone(time_Zone);
            return format.parse(time).toString();
        }






    }

}
