package com.dpwgc.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.monitor.model.response.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
}
