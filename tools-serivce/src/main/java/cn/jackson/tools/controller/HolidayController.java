package cn.jackson.tools.controller;

import cn.jackson.tools.service.holiday.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeParseException;

@RestController()
public class HolidayController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/holiday/get")
    public String hello(String date) {
        try {
            Boolean holiday = holidayService.isHoliday(date);
            if (holiday == null) {
                return "Normal";
            } else if (Boolean.TRUE.equals(holiday)) {
                return "Holiday";
            } else {
                return "Workday";
            }
        } catch (DateTimeParseException ex) {
            return "date format error eg . YYYY-MM-dd";
        } catch (Exception ex) {
            return "process fail " + ex.getMessage();
        }
    }
}
