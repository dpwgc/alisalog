package com.dpwgc.alisalog.common.util;

import com.dpwgc.alisalog.common.constant.RedisPrefix;

public class RedisKeyUtil {

    /**
     * idc数据中心旗下的主机列表key
     */
    public static String getHostListKey(String idc) {
        return String.format("%s-%s", RedisPrefix.HOST_SET,idc);
    }

    /**
     * app旗下的模块列表key
     */
    public static String getModuleListKey(String appId) {
        return String.format("%s-%s", RedisPrefix.MODULE_SET,appId);
    }

    /**
     * 模块下面的分类列表key
     */
    public static String getCategoryListKey(String appId,String module) {
        return String.format("%s-%s-%s", RedisPrefix.CATEGORY_SET,appId,module);
    }

    /**
     * 分类下面的子分类列表key
     */
    public static String getSubCategoryListKey(String appId,String module,String category) {
        return String.format("%s-%s-%s-%s", RedisPrefix.SUB_CATEGORY_SET,appId,module,category);
    }
}
