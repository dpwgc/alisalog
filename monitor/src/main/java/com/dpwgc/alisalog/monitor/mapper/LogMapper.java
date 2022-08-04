package com.dpwgc.alisalog.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.alisalog.monitor.model.response.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
}
