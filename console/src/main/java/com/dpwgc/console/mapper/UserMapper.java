package com.dpwgc.console.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.console.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
