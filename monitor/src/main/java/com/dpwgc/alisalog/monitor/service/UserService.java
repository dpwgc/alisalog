package com.dpwgc.alisalog.monitor.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.alisalog.common.constant.Status;
import com.dpwgc.alisalog.common.util.IdGenUtil;
import com.dpwgc.alisalog.common.util.Md5Util;
import com.dpwgc.alisalog.monitor.base.UserResult;
import com.dpwgc.alisalog.monitor.cache.Cache;
import com.dpwgc.alisalog.monitor.mapper.UserMapper;
import com.dpwgc.alisalog.monitor.model.request.UserLogin;
import com.dpwgc.alisalog.monitor.model.response.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public UserResult login(UserLogin userLogin) {
        //匹配用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","password");
        queryWrapper.eq("username",userLogin.getUsername());
        queryWrapper.eq("password", Md5Util.string2MD5(userLogin.getPassword()));
        queryWrapper.eq("status", Status.NORMAL);
        User user = userMapper.selectOne(queryWrapper);

        if (user != null) {
            UserResult userResult = new UserResult();
            userResult.setToken(IdGenUtil.uuid());
            userResult.setInfo(user);

            //登陆token写入缓存
            Cache.set(user.getUsername(), userResult.getToken());
            return userResult;
        } else {
            return null;
        }
    }
}
