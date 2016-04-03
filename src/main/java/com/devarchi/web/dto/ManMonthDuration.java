package com.devarchi.web.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by donghoon on 2016. 4. 3..
 */
public class ManMonthDuration {

    private static final Logger logger = LoggerFactory.getLogger(ManMonthDuration.class);

    private String[] duration;
    private String startDay;
    private String endDay;
    Integer[] splitStartDay;
    private int sDay;
    private int sMonth;
    private int sYear;
    Integer[] splitEndDay;
    private int eDay;
    private int eMonth;
    private int eYear;

    public ManMonthDuration(String duration) {
        this.duration = splitStartEndDay(duration);
        this.startDay = getDuration()[0];
        this.endDay = getDuration()[1];
        this.splitStartDay = splitYearMonthDay(startDay);
        this.sMonth = splitStartDay[0];
        this.sDay = splitStartDay[1];
        this.sYear = splitStartDay[2];
        this.splitEndDay = splitYearMonthDay(endDay);
        this.eMonth = splitEndDay[0];
        this.eDay = splitEndDay[1];
        this.eYear = splitEndDay[2];
    }

    public String[] getDuration() {
        return duration;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public Integer[] getSplitStartDay() {
        return splitStartDay;
    }

    public int getsDay() {
        return sDay;
    }

    public int getsMonth() {
        return sMonth;
    }

    public int getsYear() {
        return sYear;
    }

    public Integer[] getSplitEndDay() {
        return splitEndDay;
    }

    public int geteDay() {
        return eDay;
    }

    public int geteMonth() {
        return eMonth;
    }

    public int geteYear() {
        return eYear;
    }

    /**
     * 기간을 입력 받아 시작일 과 종료일로 분리한다.
     *
     * @param duration
     * @return
     */
    private String[] splitStartEndDay(String duration) {
        logger.debug("SplitStartEndDay: {}", duration);
        String[] splitDuration = duration.split(" - ", 2);
        return splitDuration;
    }

    /**
     * 날짜를 입력 받아 연, 월, 일 로 분리한 후 연산이 가능하도록 Integer 형태로 변환하여 리턴한다.
     *
     * @param day
     * @return
     */
    private Integer[] splitYearMonthDay(String day) {
        logger.debug("SplitYearMonthDay: {}", day);
        String[] splitYMD = day.split("/", 3);
        Integer[] cnvSplitYMD = new Integer[3];
        for (int i = 0; i < splitYMD.length; i++) {
            cnvSplitYMD[i] = Integer.parseInt(splitYMD[i]);
            logger.info("SplitYMD: {}", cnvSplitYMD[i]);
        }
        return cnvSplitYMD;
    }

}
