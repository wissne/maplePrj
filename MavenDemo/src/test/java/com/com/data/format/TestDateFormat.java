package com.com.data.format;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maple on 2014/7/9.
 */
public class TestDateFormat {

    @Test
    public void test1() {
        String format = new String("yyyy-MM-dd'T'HH:mm:ss.S XXX");

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String str = simpleDateFormat.format(date);
        System.out.println(str);
    }
}
