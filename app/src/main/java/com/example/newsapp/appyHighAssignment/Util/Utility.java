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
import java.util.concurrent.TimeUnit;

//-------------------------------------------
//THIS CLASS CONTAINS SOME UTILITY FUNCTIONS
//--------------------------------------------
public class Utility {
    public  static class CustomDate {

        public final static String getTimeDetails(String time) throws ParseException {
            final TimeZone time_Zone=TimeZone.getTimeZone("GMT");
            String customDateTimeString = "";
            String mSentOnFormat = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            format.setTimeZone(time_Zone);
            Date past = format.parse(time);
            Date now =new Date();
            long seconds= TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
            long minutes=TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
            long hours=TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
            long days=TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
            System.out.println(minutes + " minutes ago");
                DateFormat actualFormat = new SimpleDateFormat(mSentOnFormat);
                //   System.out.println("TIMEZONE: "+tz.getID()+": "+tz.getDisplayName());
                actualFormat.setTimeZone(time_Zone);
                Date dFormat = actualFormat.parse(time);
                DateFormat newsTime = new SimpleDateFormat("hh:mm a");
                DateFormat Messagedate = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat MessageDay = new SimpleDateFormat("dd");
                DateFormat MessageMonth = new SimpleDateFormat("MMM");
                DateFormat MessageYear = new SimpleDateFormat("yyyy");
                DateFormat MessageDM = new SimpleDateFormat("dd-MMM");
                String messageTime = newsTime.format(dFormat);
                String messageDM = MessageDM.format(dFormat);
                String newsDate=Messagedate.format(dFormat);
                customDateTimeString = newsDate+": "+messageTime;
                return customDateTimeString;
        }






    }

}
