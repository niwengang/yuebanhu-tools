package cn.jackson.tools.service.holiday;

import cn.jackson.tools.dao.HolidayAdjustmentDao;
import cn.jackson.tools.entity.HolidayAdjustment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HolidayService
 *
 * @author Jackson.Ni
 * @since 2022/10/26
 */
@Service
public class HolidayService {

    private final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Logger log = LoggerFactory.getLogger(HolidayService.class);

    private final HolidayAdjustmentDao holidayAdjustmentDao;

    private Map<LocalDate, Boolean> holidayMap = new HashMap<>();

    @Autowired
    public HolidayService(HolidayAdjustmentDao holidayAdjustmentDao) {
        this.holidayAdjustmentDao = holidayAdjustmentDao;
    }

    /**
     * 判断输入的日期是否为休息日
     * 1. 读取holidayMap 若命中 直接返回结果
     * 2. 不命中情况下
     * @param dateString
     * @return true 假期 false 工作日 null 没有查到结果
     * @throws DateTimeParseException 日期不合法
     *
     */
    public Boolean isHoliday(String dateString) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateString, YYYY_MM_DD);
        return holidayMap.get(date);
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    @PostConstruct
    public void readData() {
        List<HolidayAdjustment> holidayAdjustments = holidayAdjustmentDao.queryAll();

        if (CollectionUtils.isEmpty(holidayAdjustments)) {
            log.info("no holiday data in database");
            return;
        }
        // 循环每个配置将所有日期加入到内存中
        holidayAdjustments.forEach(holidayAdjustment -> {
            LocalDate fromDate = LocalDate.parse(holidayAdjustment.getDateFrom(), YYYY_MM_DD);
            LocalDate toDate = LocalDate.parse(holidayAdjustment.getDateTo(), YYYY_MM_DD);
            boolean isHoliday = holidayAdjustment.isHoliday();
            // 当天处理日期
            LocalDate currentDate = fromDate;
            while (toDate.compareTo(currentDate) >= 0){
                holidayMap.put(currentDate, isHoliday);
                currentDate = currentDate.plusDays(1);
                log.info("date {} isHoliday {}", currentDate, isHoliday);
            }
        });
    }
}
