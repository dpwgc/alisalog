package com.dpwgc.console.base;

import com.dpwgc.common.constant.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "统一返回模板-DTO")
public class ApiResult<T> {

    @ApiModelProperty(value = "请求处理是否成功")
    protected boolean success;

    @ApiModelProperty(value = "响应代码")
    protected Integer code;

    @ApiModelProperty(value = "响应信息")
    protected String message;

    @ApiModelProperty(value = "响应数据")
    protected T data;

    public Integer getCode() {
        return this.code;
    }

    public ApiResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResult() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ApiResult<T> getSuccessResult(T v) {
        ApiResult<T> apiResult = new ApiResult();
        apiResult.setSuccess(true);
        apiResult.setCode(Code.SUCCESS);
        apiResult.setMessage("success");
        apiResult.setData(v);
        return apiResult;
    }

    public static <T> ApiResult<T> getFailureResult(String msg) {
        ApiResult<T> apiResult = new ApiResult();
        apiResult.setSuccess(false);
        apiResult.setCode(Code.ERROR);
        apiResult.setMessage(msg);
        return apiResult;
    }
}
