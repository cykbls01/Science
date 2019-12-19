package com.example.demo.Util;
import com.example.demo.Entity.Resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Time {

    public static String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return df.format(new Date());



    }

    public static boolean compare(String time1,String time2) throws ParseException {

        if(time1.length()>4)
            time1.substring(0,4);
        if(time2.length()>4)
            time2.substring(0,4);

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);

        int compareTo = date1.compareTo(date2);

        if(compareTo==-1)
            return  false;
        else return  true;


    }

    public static List<Resources> sort(List<Resources> resourcesList) throws ParseException {

        int size=resourcesList.size();
        for(int i=0;i<size-1;i++)
        {
            for(int j=0;j<size-1;j++)
            {
                if (Time.compare(resourcesList.get(j).getTime(),resourcesList.get(j+1).getTime())==false)
                {
                    Collections.swap(resourcesList,j,j+1);
                }
            }
        }
        return resourcesList;

    }




}
