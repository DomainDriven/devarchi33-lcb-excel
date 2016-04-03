package com.devarchi.web.controller;

import com.devarchi.web.dto.ManMonthDuration;
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

    private ManMonthDuration duration;
    private final String APP_DIR = "home/";

    @RequestMapping("manMonth")
    public String manMonth(Model model) {
        model.addAttribute("greeting", "Hello");
        return APP_DIR + "manmonth";
    }

    @RequestMapping(value = "calcManMonth", method = RequestMethod.POST)
    @ResponseBody
    public String manMonth(@RequestParam String duration) {
        logger.info("Input Duration: {}", duration);

        Float result = calcManMonth(duration);

        logger.info("Result: {}", result);
        return result.toString() + " month";
    }

    /**
     * 입력 받은 날짜의 월의 마지막 날을 구하여 리턴한다.
     *
     * @param date
     * @return
     */
    private int getEndOfMonth(Integer[] date) {
        /**
         * cal.set(year, month, day)
         */
        cal.set(date[2], date[0] - 1, date[1]);
        int endOfMonth = cal.getActualMaximum(Calendar.DATE);
        logger.info("EndOfMonth: {}", endOfMonth);
        return endOfMonth;
    }

    /**
     * 기간을 입력 받아 manMonth 를 계산하여 결과 값을 리턴한다.
     *
     * @param inputDuration
     * @return
     */
    private float calcManMonth(String inputDuration) {
        logger.info("CalcManMonth: {}", inputDuration);
        duration = new ManMonthDuration(inputDuration);

        String startDay = duration.getStartDay();
        Integer endOfStartDay = getEndOfMonth(duration.getSplitStartDay());
        Integer sYear = duration.getsYear();
        Integer sMonth = duration.getsMonth();
        Integer sDay = duration.getsDay();

        String endDay = duration.getEndDay();
        Integer endOfEndDay = getEndOfMonth(duration.getSplitEndDay());
        Integer eYear = duration.geteYear();
        Integer eMonth = duration.geteMonth();
        Integer eDay = duration.geteDay();

        logger.info("SplitDay -> StartDay : {}, EndDay : {}", startDay, endDay);
        logger.info("Start -> Year : {}, Month : {}, Day : {}", sYear, sMonth, sDay);
        logger.info("End -> Year : {}, Month : {}, Day : {}", eYear, eMonth, eDay);
        logger.info("EndOfStartDay: {}", endOfStartDay);
        logger.info("EndOfEndDay: {}", endOfEndDay);

        float result;

        /**
         * ManMonth 핵심 로직.
         */
        if (sDay == eDay + 1) {
            result = (float) ((eYear - sYear) * 12 + (eMonth - sMonth));
        } else {
            result = (eYear - sYear) * 12 + (eMonth - sMonth - 1)
                    + (float) ((endOfStartDay - sDay + 1) / endOfStartDay)
                    + (float) (eDay / endOfEndDay);
        }

        return result;
    }

//    @Autowired
//    private MemberRepository memberRepository;
//
//    @RequestMapping("home")
//    assets String sayHello(Model model) {
//        model.addAttribute("greeting", "Hello");
//        return APP_DIR + "index";
//    }
//
//    @RequestMapping("/")
//    @ResponseBody
//    assets String home() {
//        return "Hello, springboot";
//    }
//
//    @RequestMapping("list")
//    @ResponseBody
//    assets String memberList() {
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
//    assets String onePerson() {
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
//    assets void memberAdd() {
//        memberRepository.save(new Member("face", "new"));
//        memberRepository.save(new Member("face", "old"));
//    }
}
