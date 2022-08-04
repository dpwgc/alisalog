package com.dpwgc.alisalog.monitor.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Worker节点信息")
public class Node {
    @ApiModelProperty(value = "节点IP地址")
    String address;
    @ApiModelProperty(value = "节点UDP服务端口号")
    String udpPort;
    @ApiModelProperty(value = "节点HTTP服务端口号")
    String httpPort;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }

    public void setUdpPort(String udpPort) {
        this.udpPort = udpPort;
    }

    public String getAddress() {
        return address;
    }

    public String getHttpPort() {
        return httpPort;
    }

    public String getUdpPort() {
        return udpPort;
    }
}
