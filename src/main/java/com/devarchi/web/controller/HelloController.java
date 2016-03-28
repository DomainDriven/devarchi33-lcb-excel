package com.devarchi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

/**
 * Created by donghoon on 2016. 2. 25..
 */
@Controller
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private static final Calendar cal = Calendar.getInstance();

    private final String APP_DIR = "home/";

    @RequestMapping("manMonth")
    public String manMonth(Model model) {
        model.addAttribute("greeting", "Hello");
        return APP_DIR + "manmonth";
    }

    @RequestMapping(value = "calcManMonth", method = RequestMethod.POST)
    @ResponseBody
    public String manMonth(@RequestParam String duration) {
        logger.info("Input Duration: " + duration);

        Integer result = calcManMonth(duration);

        return result.toString() + " 개월";
    }

    /**
     * 기간을 입력 받아 시작일 과 종료일로 분리한다.
     *
     * @param duration
     * @return
     */
    private String[] splitStartEndDay(String duration) {
        logger.debug("SplitStartEndDay: " + duration);
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
        logger.debug("SplitYearMonthDay: " + day);
        String[] splitYMD = day.split("/", 3);
        Integer[] cnvSplitYMD = new Integer[3];
        for (int i = 0; i < splitYMD.length; i++) {
            cnvSplitYMD[i] = Integer.parseInt(splitYMD[i]);
            logger.info("CnvSplitYMD: " + cnvSplitYMD[i]);
        }
        return cnvSplitYMD;
    }

    /**
     * 입력 받은 날짜의 월의 마지막 날을 구하여 리턴한다.
     *
     * @param date
     * @return
     */
    private int getEndOfMonth(Integer[] date) {
        logger.debug("GetEndOfMonth: " + date);
        cal.set(date[0], date[1] - 1, date[2]);
        int endOfMonth = cal.getActualMaximum(Calendar.DATE);
        return endOfMonth;
    }

    /**
     * 기간을 입력 받아 manMonth 를 계산하여 결과 값을 리턴한다.
     *
     * @param duration
     * @return
     */
    private int calcManMonth(String duration) {
        logger.debug("CalcManMonth: " + duration);
        String[] splitStartEndDay = splitStartEndDay(duration);

        String startDay = splitStartEndDay[0];
        Integer[] startYMD = splitYearMonthDay(startDay);
        Integer endOfStartDay = getEndOfMonth(startYMD);
        Integer sYear = startYMD[2];
        Integer sMonth = startYMD[0];
        Integer sDay = startYMD[1];

        String endDay = splitStartEndDay[1];
        Integer[] endYMD = splitYearMonthDay(endDay);
        Integer endOfEndDay = getEndOfMonth(endYMD);
        Integer eYear = endYMD[2];
        Integer eMonth = endYMD[0];
        Integer eDay = endYMD[1];

        logger.info("SplitDay -> StartDay : {}, EndDay : {}", startDay, endDay);
        logger.info("Start -> Year : {}, Month : {}, Day : {}", sYear, sMonth, sDay);
        logger.info("End -> Year : {}, Month : {}, Day : {}", eYear, eMonth, eDay);

        int result = 0;

        /**
         * ManMonth 핵심 로직.
         */
        if (sDay == eDay + 1) {
            result = (eYear - sYear) * 12 + (eMonth - sMonth);
        } else {
            result = (eYear - sYear) * 12 + (eMonth - sMonth - 1)
                    + ((endOfStartDay - sDay + 1) / endOfStartDay)
                    + (eDay / endOfEndDay);
        }

        return result;
    }

//    @Autowired
//    private MemberRepository memberRepository;
//
//    @RequestMapping("home")
//    public String sayHello(Model model) {
//        model.addAttribute("greeting", "Hello");
//        return APP_DIR + "index";
//    }
//
//    @RequestMapping("/")
//    @ResponseBody
//    public String home() {
//        return "Hello, springboot";
//    }
//
//    @RequestMapping("list")
//    @ResponseBody
//    public String memberList() {
//        List<Member> memberList = new ArrayList<Member>();
//
//        for (Member member : memberRepository.findAll()) {
//            memberList.add(member);
//        }
//
//        return memberList.toString();
//    }
//
//    @RequestMapping("listByLastName")
//    @ResponseBody
//    public String onePerson() {
//        List<Member> memberList = new ArrayList<Member>();
//
//        for (Member member : memberRepository.findByLastName("이")) {
//            memberList.add(member);
//        }
//
//        return memberList.toString();
//    }
//
//    @RequestMapping("add")
//    public void memberAdd() {
//        memberRepository.save(new Member("face", "new"));
//        memberRepository.save(new Member("face", "old"));
//    }
}
