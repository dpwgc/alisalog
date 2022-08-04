package com.dpwgc.alisalog.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.alisalog.monitor.model.response.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
