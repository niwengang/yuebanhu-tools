package cn.jackson.tools.dao;

import cn.jackson.tools.entity.HolidayAdjustment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayAdjustmentDao {

    List<HolidayAdjustment> queryAll();
}
