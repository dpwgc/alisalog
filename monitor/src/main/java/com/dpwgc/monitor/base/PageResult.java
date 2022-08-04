package com.dpwgc.monitor.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分页返回模板")
public class PageResult<T> {

    @ApiModelProperty(value = "数据总数")
    private Long total;

    @ApiModelProperty(value = "当前分页区间内的数据列表")
    private T list;

    public static <T> PageResult<T> getPageBase(Long total, T list) {
        PageResult<T> pageResult = new PageResult();
        pageResult.total = total;
        pageResult.list = list;
        return pageResult;
    }

    public void setList(T list) {
        this.list = list;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public T getList() {
        return list;
    }
}
