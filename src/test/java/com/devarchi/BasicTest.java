package com.devarchi;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by donghoon on 2016. 3. 29..
 */
public class BasicTest {

    private Date date;
    private String time;

    @Before
    public void setup() {
        date = new Date();
        time = date.toString();
    }

    @Test
    public void stringHandleTest() {
        System.out.println(time);
        time = time.replace(" ", ":");
        System.out.println(time);

        String duration = "03/28/2016 - 03/31/2016";
        String trDuration = duration.trim();
        System.out.println(trDuration);
        StringTokenizer st = new StringTokenizer(duration, " - ");
        while (st.hasMoreElements()) {
            System.out.println(st.nextToken());
        }

        String[] durations = duration.split(" - ", 2);
        for (int i = 0; i < durations.length; i++) {
            System.out.println(durations[i]);
        }

        String year = "2015";
        String nyear = year + 1;
        System.out.println(nyear);

        Calendar cal = Calendar.getInstance();

        cal.set(2016, 2 - 1, 28);
        int endOfMonth = cal.getActualMaximum(Calendar.DATE);
        System.out.println(endOfMonth);
    }
}
